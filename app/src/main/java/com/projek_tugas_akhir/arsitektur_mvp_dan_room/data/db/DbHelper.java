package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

public interface DbHelper {

    Flowable<Boolean> insertHospital(Hospital hospital);

    Flowable<Boolean> insertHospitalList(List<Hospital> hospitalList);

    Flowable<Boolean> insertMedicine(Medicine medicine);

    Flowable<Boolean> insertMedicineList(List<Medicine> medicineList);

    Flowable<Boolean> deleteHospital(Hospital hospital);

    Flowable<Boolean> deleteMedicine(Medicine medicine);

    Flowable<Hospital> loadHospital(Hospital hospital);

    Flowable<Medicine> loadMedicine(Medicine medicine);

    Flowable<List<Hospital>> getAllHospital();

    Flowable<List<Medicine>> getAllMedicine();

    Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId);

    Flowable<Boolean> isHospitalEmpty();

    Flowable<Boolean> isMedicineEmpty();

    Flowable<Boolean> saveHospital(Hospital hospital);

    Flowable<Boolean> saveHospitalList(List<Hospital> hospitalList);

    Flowable<Boolean> saveMedicine(Medicine medicine);

    Flowable<Boolean> saveMedicineList(List<Medicine> medicineList);

}
