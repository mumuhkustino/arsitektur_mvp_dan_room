package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

public class DeletePresenter <V extends DeleteMvpView> extends BasePresenter<V> implements DeleteMvpPresenter<V> {

    private static final String TAG = "DeletePresenter";

    @Inject
    public DeletePresenter(DataManager mDataManager,
                           SchedulerProvider mSchedulerProvider,
                           CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    public void deleteDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger(0);
//        List<Medicine> medicines = new ArrayList<>();
//        //                    for (int i = 0; i < medicineList.size(); i++) {
//        //                        if (index.get() < numOfData) {
//        //                            medicines.add(medicineList.get(i));
//        //                            index.getAndIncrement();
//        //                        } else
//        //                            break;
//        //                    }
//        getCompositeDisposable().add(getDataManager()
//            .getAllHospital(numOfData > 1000 ? numOfData / 1000 : numOfData)
//                .concatMap(Flowable::fromIterable)
//                    .concatMap(hospital -> Flowable.zip(
//                            getDataManager().getMedicinesForHospitalId(hospital.id),
//                            Flowable.just(hospital),
//                            ((medicineList, hospital1) -> {
//                                for (Medicine m : medicineList) {
//                                    if (index.get() < numOfData) {
//                                        medicines.add(m);
//                                        index.getAndIncrement();
//                                    }
//                                }
//                                return medicines;
//                            })
//                    ))
//                .concatMap(Flowable::fromIterable)
//                .concatMap(medicine -> getDataManager().deleteDatabaseMedicine(medicine))
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (!isViewAttached())
//                        return;
//                    if (index.get() == numOfData) {
//                        getMvpView().updateNumOfRecordDelete(index.longValue());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        getMvpView().updateExecutionTimeDelete(timeElapsed); //To MilliSeconds
//                        index.getAndIncrement();
//                    } else if (index.get() == numOfData + 1) {
//                        Log.d(TAG, "deleteDatabase: " + index.get());
//                        index.getAndIncrement();
//                    }
//                }, throwable -> {
//                    if (!isViewAttached())
//                        return;
//                    getMvpView().stateLoadingDelete(false);
//                    getMvpView().onError(R.string.some_error);
//                    Log.d(TAG, "deleteDatabase: " + throwable.getMessage());
//                }));

        long startTime = System.currentTimeMillis();
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
                //Delete medicine name with object
                .concatMap(Flowable::fromIterable)
                    .concatMap(medicine -> {
                        if (index.get() < numOfData) {
                            index.getAndIncrement();
                            return getDataManager().deleteDatabaseMedicine(medicine);
                        }
                        return Flowable.just(false);
                    })
            .observeOn(getSchedulerProvider().ui())
            .subscribe(aBoolean -> {
                if (!isViewAttached())
                    return;
                if (index.get() == numOfData) {
                    getMvpView().updateNumOfRecordDelete(index.longValue()); //Change number of record
                    long endTime = System.currentTimeMillis();
                    long timeElapsed = endTime - startTime; //In MilliSeconds
                    getMvpView().updateExecutionTimeDelete(timeElapsed); //Change execution time
                    Log.d(TAG, "deleteDatabase: " + index.get());
                    index.getAndIncrement();
                }
            }, throwable -> Log.d(TAG, "deleteDatabase: " + throwable.getMessage())
            )
        );
    }

    @Override
    public void onDeleteExecuteClick(Long numOfData) {
        deleteDatabase(numOfData);
    }
}
