package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.ExecutionTime;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.ExecutionTimePreference;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

public class UpdatePresenter<V extends UpdateMvpView> extends BasePresenter<V> implements UpdateMvpPresenter<V> {

    private static final String TAG = "UpdatePresenter";

    @Inject
    public UpdatePresenter(DataManager mDataManager,
                           SchedulerProvider mSchedulerProvider,
                           CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    public void updateDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewUpdateTime = new AtomicLong(0);
        AtomicLong updateDbTime = new AtomicLong(0);
        AtomicLong updateTime = new AtomicLong(0);
        AtomicLong allUpdateTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger index = new AtomicInteger(0);
        getCompositeDisposable().add(getDataManager()
            //Get All Hospital with Limit
            .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                    //Get All Medicine with same hospital Id
                    .concatMap(hospital -> Flowable.zip(
                        getDataManager().getMedicinesForHospitalId(hospital.id),
                        Flowable.just(hospital),
                        ((medicineList, hospital1) -> medicineList)
                    ))
                //Update medicine name with addition new
                .concatMap(medicineList -> {
                    for (int i = 0; i < medicineList.size(); i++) {
                        if (index.get() < numOfData) {
                            medicineList.get(i).name = medicineList.get(i).name + " NEW";
                            index.getAndIncrement();
                        } else
                            break;
                    }
                    return Flowable.fromIterable(medicineList);
                })
                    .concatMap(medicine -> {
                        updateTime.set(System.currentTimeMillis());
                        return getDataManager().updateDatabaseMedicine(medicine);
                    })
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        updateDbTime.set(updateDbTime.longValue() + (System.currentTimeMillis() - updateTime.longValue()));
                })
            .observeOn(getSchedulerProvider().ui())
            .subscribe(aBoolean -> {
                if (!isViewAttached())
                    return;
                if (index.get() == numOfData) {
                    getMvpView().updateNumOfRecordUpdate(index.longValue());
                    getMvpView().updateUpdateDatabaseTime(updateDbTime.longValue()); //Change execution time
                    AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                    AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allUpdateTime.longValue());
                    viewUpdateTime.set(timeElapsed.get() - updateDbTime.longValue());
                    getMvpView().updateViewUpdateTime(viewUpdateTime.longValue());
                    getMvpView().updateAllUpdateTime(timeElapsed.longValue());

                    ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                    executionTime.setDatabaseUpdateTime(updateDbTime.toString());
                    executionTime.setAllUpdateTime(timeElapsed.toString());
                    executionTime.setViewUpdateTime(viewUpdateTime.toString());
                    executionTime.setNumOfRecordUpdate(numOfData.toString());
                    executionTimePreference.setExecutionTime(executionTime);

                    Log.d(TAG, "updateDatabase: " + index.longValue());
                    index.getAndIncrement();
                }
            }, throwable -> Log.d(TAG, "updateDatabase: " + throwable.getMessage())
            )
        );
    }

    @Override
    public void onUpdateExecuteClick(ExecutionTimePreference executionTimePreference, Long numOfData) {
        updateDatabase(executionTimePreference, numOfData);
    }
}
