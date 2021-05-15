package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

public class SelectPresenter<V extends SelectMvpView> extends BasePresenter<V> implements SelectMvpPresenter<V> {

    private static final String TAG = "SelectPresenter";

    @Inject
    public SelectPresenter(DataManager mDataManager,
                           SchedulerProvider mSchedulerProvider,
                           CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    public void selectDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        getCompositeDisposable().add(getDataManager()
            //Get All Hospital with Limit
            .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                    //Get All Medicine with same hospital Id
                    .concatMap(hospital -> Flowable.zip(
                        getDataManager().getMedicinesForHospitalId(hospital.id),
                        Flowable.just(hospital),
                        ((medicineList, h) -> {
                            for (Medicine m : medicineList) {
                                if (index.get() < numOfData) {
                                    medicals.add(new Medical(h.name, m.name));
                                    index.getAndIncrement();
                                }
                            }
                            return medicals;
                        })
                    ))
            .observeOn(getSchedulerProvider().ui())
            .subscribe(medicalList -> {
                if (!isViewAttached())
                    return;
                if (medicalList != null && index.get() == numOfData) {
                    getMvpView().selectMedicalData(medicalList); //Change data list
                    getMvpView().updateNumOfRecordSelect(index.longValue()); //Change number of record
                    long endTime = System.currentTimeMillis();
                    long timeElapsed = endTime - startTime; //In MilliSeconds
                    getMvpView().updateExecutionTimeSelect(timeElapsed); //Change execution time
                    Log.d(TAG, "selectDatabase: " + index.get());
                    index.getAndIncrement();
                }
            }, throwable -> Log.d(TAG, "selectDatabase: " + throwable.getMessage()))
        );
    }

    @Override
    public void onSelectExecuteClick(Long numOfData) {
        selectDatabase(numOfData);
    }
}
