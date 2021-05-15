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

    @Delete
    void delete(Medicine medicine);

    @Query("SELECT * FROM medicines WHERE id = :id")
    Flowable<Medicine> load(Long id);

    @Query("SELECT * FROM medicines WHERE hospitalId = :hospitalId")
    Flowable<List<Medicine>> loadAllByHospitalId(Long hospitalId);

    @Query("SELECT * FROM medicines")
    Flowable<List<Medicine>> loadAll();

    @Query("SELECT * FROM medicines LIMIT :numOfData")
    Flowable<List<Medicine>> loadList(Long numOfData);

    @Update
    void save(Medicine medicine);

}
