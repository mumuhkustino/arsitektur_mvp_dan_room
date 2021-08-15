package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.others.ExecutionTime;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.others.ExecutionTimePreference;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BasePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

public class InsertPresenter<V extends InsertMvpView> extends BasePresenter<V> implements InsertMvpPresenter<V> {

    private static final String TAG = "InsertPresenter";

    @Inject // Penggunaan dependency injection pada instance insert presenter
    public InsertPresenter(DataManager mDataManager,
                           SchedulerProvider mSchedulerProvider,
                           CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    // Method yang digunakan untuk insert data
    public void insertDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewInsertTime = new AtomicLong(0);
        AtomicLong insertDbTime = new AtomicLong(0);
        AtomicLong insertTime = new AtomicLong(0);
        AtomicLong allInsertTime = new AtomicLong(System.currentTimeMillis());
        //Insert Hospital JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
                .concatMap(hospitals -> {
                    insertTime.set(System.currentTimeMillis());
                    return getDataManager().insertHospitals(hospitals);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        insertDbTime.set(insertDbTime.longValue() + (System.currentTimeMillis() - insertTime.longValue()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                        }, throwable -> Log.d("IVM", "insertDatabase 1: " + throwable.getMessage())
                )
        );
        //Insert Medicine JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseMedicine(numOfData)
                .concatMap(medicines -> {
                    insertTime.set(System.currentTimeMillis());
                    return getDataManager().insertMedicines(medicines);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        insertDbTime.set(insertDbTime.longValue() + (System.currentTimeMillis() - insertTime.longValue()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                if (!isViewAttached())
                    return;
                if (aBoolean) {
                    getMvpView().updateNumOfRecordInsert(numOfData);
                    getMvpView().updateInsertDatabaseTime(insertDbTime.longValue()); //Change execution time
                    AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                    AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allInsertTime.longValue());
                    viewInsertTime.set(timeElapsed.get() - insertDbTime.longValue());
                    getMvpView().updateViewInsertTime(viewInsertTime.longValue());
                    getMvpView().updateAllInsertTime(timeElapsed.longValue());

                    ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                    executionTime.setDatabaseInsertTime(insertDbTime.toString());
                    executionTime.setAllInsertTime(timeElapsed.toString());
                    executionTime.setViewInsertTime(viewInsertTime.toString());
                    executionTime.setNumOfRecordInsert(numOfData.toString());
                    executionTimePreference.setExecutionTime(executionTime);
                }
            } , throwable -> Log.d(TAG, "insertDatabase: " + throwable.getMessage())
            )
        );
    }

    @Override
    public void onInsertExecuteClick(ExecutionTimePreference executionTimePreference, Long numOfData) {
        insertDatabase(executionTimePreference, numOfData);
    }
}
