package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.dao.HospitalDao;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.dao.MedicineDao;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Medicine;

@Database(entities = {Hospital.class, Medicine.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HospitalDao hospitalDao();

    public abstract MedicineDao medicineDao();

}