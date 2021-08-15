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
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().load(hospital.id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().load(medicine.id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().loadAll();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital(Long numOfData) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().loadList(numOfData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadAll();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine(Long numOfData) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadList(numOfData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadAllByHospitalId(hospitalId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().save(hospital);
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
                mAppDatabase.medicineDao().save(medicine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

}
