package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface HospitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Hospital hospital);

    @Delete
    void delete(Hospital hospital);

    @Query("SELECT * FROM hospitals WHERE id = :hospitalId")
    Flowable<Hospital> load(Long hospitalId);

    @Query("SELECT * FROM hospitals")
    Flowable<List<Hospital>> loadAll();

    @Query("SELECT * FROM hospitals LIMIT :numOfData")
    Flowable<List<Hospital>> loadList(Long numOfData);

    @Update
    void save(Hospital hospital);

}
