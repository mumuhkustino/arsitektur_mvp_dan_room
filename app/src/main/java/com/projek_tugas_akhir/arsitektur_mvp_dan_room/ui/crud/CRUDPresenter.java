//package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;
//
//import android.util.Log;
//
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
//import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import javax.inject.Inject;
//
//import io.reactivex.Flowable;
//import io.reactivex.disposables.CompositeDisposable;
//
//public class CRUDPresenter<V extends CRUDMvpView> extends BasePresenter<V> implements CRUDMvpPresenter<V> {
//
//    private static final String TAG = "CRUDPresenter";
//
//    @Inject
//    public CRUDPresenter(DataManager mDataManager,
//                         SchedulerProvider mSchedulerProvider,
//                         CompositeDisposable mCompositeDisposable) {
//        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
//    }
//
//    public void insertDatabase(Long numOfData) {
//        getMvpView().hideKeyboard();
//        getMvpView().stateLoadingInsert(true);
//        long startTime = System.currentTimeMillis();
//        getCompositeDisposable().add(getDataManager()
//                .seedDatabaseHospital(numOfData)
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(aBoolean -> getDataManager().seedDatabaseMedicine(numOfData))
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                            if (!isViewAttached())
//                                return;
//                            if (aBoolean) {
//                                getMvpView().updateNumOfRecordInsert(numOfData);
//                                long endTime = System.currentTimeMillis();
//                                long timeElapsed = endTime - startTime; //In MilliSeconds
//                                getMvpView().updateExecutionTimeInsert(timeElapsed); //To MilliSeconds
//                                Log.d(TAG, "insertDatabase: " + numOfData);
//                            }
//                            getMvpView().stateLoadingInsert(false);
//                        }
//                        , throwable -> {
//                            if (!isViewAttached())
//                                return;
//                            getMvpView().stateLoadingInsert(false);
//                            getMvpView().onError(R.string.some_error);
//                            Log.d(TAG, "insertDatabase: " + throwable.getMessage());
//                        }
//                )
//        );
//    }
//
//    public void selectDatabase(Long numOfData) {
//        getMvpView().hideKeyboard();
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger();
//        index.set(0);
//        getMvpView().selectMedicalData(new ArrayList<>());
//        List<Medical> medicals = new ArrayList<>();
//        getCompositeDisposable().add(getDataManager()
//                .getAllHospital()
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospitals -> Flowable.fromIterable(hospitals)
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .concatMap(hospital -> {
//                            if (hospital != null) {
//                                return getDataManager().getMedicinesForHospitalId(hospital.id)
//                                        .observeOn(getSchedulerProvider().ui())
//                                        .subscribeOn(getSchedulerProvider().io())
//                                        .concatMap(medicineList -> {
//                                            if (medicineList != null) {
//                                                int j = 0;
//                                                while (j < medicineList.size() && index.get() < numOfData) {
//                                                    medicals.add(new Medical(hospital.name,
//                                                            medicineList.get(j).name));
//                                                    index.set(index.get() + 1);
//                                                    j++;
//                                                }
//                                                return Flowable.just(true);
//                                            } else
//                                                return Flowable.just(false);
//                                        });
//                            } else {
//                                return Flowable.just(false);
//                            }
//                        })
//                )
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (!isViewAttached())
//                        return;
//                    if (aBoolean) {
//                        getMvpView().selectMedicalData(medicals);
//                        getMvpView().updateNumOfRecordSelect(index.longValue());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        getMvpView().updateExecutionTimeSelect(timeElapsed); //To MilliSeconds
//                    } else
//                        Log.d(TAG, "selectDatabase: " + index.get());
//                }, throwable -> {
//                    if (!isViewAttached())
//                        return;
//                    getMvpView().stateLoadingSelect(false);
//                    getMvpView().onError(R.string.some_error);
//                    Log.d(TAG, "selectDatabase: " + throwable.getMessage());
//                }));
//    }
//
//    public void updateDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger();
//        index.set(0);
//        getCompositeDisposable().add(getDataManager()
//                .getAllHospital()
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospitalList -> Flowable.fromIterable(hospitalList)
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .concatMap(hospital -> getDataManager().getMedicinesForHospitalId(hospital.id)
//                                .observeOn(getSchedulerProvider().ui())
//                                .subscribeOn(getSchedulerProvider().io())
//                                .concatMap(medicineList -> {
//                                    if (hospital != null && medicineList != null) {
//                                        int j = 0;
//                                        while (j < medicineList.size() && index.get() < numOfData) {
//                                            medicineList.get(j).name = medicineList.get(j).name.concat(" NEW");
//                                            index.getAndIncrement();
//                                            j++;
//                                        }
////                                            }
////                                            if (index.get() < numOfData) {
////                                                medicine.name = medicine.name + " NEW";
////                                                Log.d(TAG, "updateDatabase: " + medicine.name);
////                                                index.set(index.get() + 1);
//                                        return Flowable.fromIterable(medicineList)
//                                                .concatMap(medicine -> getDataManager().updateDatabaseMedicine(medicine));
//                                    } else
//                                        return Flowable.just(false);
//                                })
//                                    .observeOn(getSchedulerProvider().ui())
//                                    .subscribeOn(getSchedulerProvider().io())
////                                )
//                        )
//                )
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (!isViewAttached())
//                        return;
//                    if (aBoolean) {
//                        getMvpView().updateNumOfRecordUpdate(index.longValue());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        getMvpView().updateExecutionTimeUpdate(timeElapsed); //To MilliSeconds
//                    } else
//                        Log.d(TAG, "updateDatabase: " + index.get());
//                }, throwable -> {
//                    if (!isViewAttached())
//                        return;
//                    getMvpView().stateLoadingUpdate(false);
//                    getMvpView().onError(R.string.some_error);
//                    Log.d(TAG, "updateDatabase: " + throwable.getMessage());
//                }));
//    }
//
//    public void deleteDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger();
//        index.set(numOfData.intValue());
//        getCompositeDisposable().add(getDataManager()
//                .getAllHospital()
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospitalList -> Flowable.fromIterable(hospitalList)
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .concatMap(hospital -> getDataManager().getMedicinesForHospitalId(hospital.id)
//                                .observeOn(getSchedulerProvider().ui())
//                                .subscribeOn(getSchedulerProvider().io())
//                                .concatMap(medicineList -> Flowable.fromIterable(medicineList)
//                                        .observeOn(getSchedulerProvider().ui())
//                                        .subscribeOn(getSchedulerProvider().io())
//                                        .concatMap(medicine -> {
//                                            if (index.get() >= 0) {
//                                                index.getAndDecrement();
//                                                return getDataManager().deleteDatabaseMedicine(medicine);
//                                            } else
//                                                return Flowable.just(false);
//                                        })
//                                )
//                        )
//                )
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (!isViewAttached())
//                        return;
//                    if (aBoolean) {
//                        getMvpView().updateNumOfRecordDelete((long) index.get());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        getMvpView().updateExecutionTimeDelete(timeElapsed); //To MilliSeconds
//                    } else
//                        Log.d(TAG, "deleteDatabase: " + index.get());
//                }, throwable -> {
//                    if (!isViewAttached())
//                        return;
//                    getMvpView().stateLoadingDelete(false);
//                    getMvpView().onError(R.string.some_error);
//                    Log.d(TAG, "deleteDatabase: " + throwable.getMessage());
//                }));
//    }
//
//    @Override
//    public void onInsertExecuteClick(Long numOfData) {
//        insertDatabase(numOfData);
//    }
//
//    @Override
//    public void onSelectExecuteClick(Long numOfData) {
//        selectDatabase(numOfData);
//    }
//
//    @Override
//    public void onUpdateExecuteClick(Long numOfData) {
//        updateDatabase(numOfData);
//    }
//
//    @Override
//    public void onDeleteExecuteClick(Long numOfData) {
//        deleteDatabase(numOfData);
//    }
//
//}
