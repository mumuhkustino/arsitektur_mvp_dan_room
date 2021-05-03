package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Medicine medicine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Medicine> medicineList);

    @Delete
    Flowable<Boolean> delete(Medicine medicine);

    @Query("SELECT * FROM medicine WHERE id = :id")
    Flowable<Medicine> load(Long id);

    @Query("SELECT * FROM medicine WHERE hospitalId = :hospitalId")
    Flowable<List<Medicine>> loadAllByHospitalId(Long hospitalId);

    @Query("SELECT * FROM medicine")
    Flowable<List<Medicine>> loadAll();

    @Update
    Flowable<Boolean> save(Medicine medicine);

    @Update
    Flowable<Boolean> saveList(List<Medicine> medicine);

}
