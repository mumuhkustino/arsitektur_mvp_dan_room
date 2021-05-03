package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Medicine;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }

    @Override
    public Flowable<Boolean> insertHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().insert(hospital);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> insertMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().insert(medicine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().delete(hospital);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().delete(medicine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return mAppDatabase.hospitalDao().load(hospital.id);
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return mAppDatabase.medicineDao().load(medicine.id);
    }

    @Override
    public Flowable<List<Hospital>> getAllHospitals() {
        return mAppDatabase.hospitalDao().loadAll();
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return mAppDatabase.medicineDao().loadAll();
    }

    @Override
    public Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId) {
        return mAppDatabase.medicineDao().loadAllByHospitalId(hospitalId);
    }

    @Override
    public Flowable<Boolean> isHospitalEmpty() {
        return mAppDatabase.hospitalDao().loadAll()
                .flatMap(hospitals -> Flowable.just(hospitals.isEmpty()));
    }

    @Override
    public Flowable<Boolean> isMedicineEmpty() {
        return mAppDatabase.medicineDao().loadAll()
                .flatMap(medicines -> Flowable.just(medicines.isEmpty()));
    }

    @Override
    public Flowable<Boolean> saveHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().insert(hospital);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().insertAll(hospitalList);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().insert(medicine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().insertAll(medicineList);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
