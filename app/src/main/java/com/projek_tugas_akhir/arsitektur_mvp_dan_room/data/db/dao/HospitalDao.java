package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model.Hospital;

import java.util.List;

@Dao
//Interface untuk model Hospital pada database
public interface HospitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Annotation untuk insert
    void insert(Hospital hospital);

    @Delete //Annotation untuk delete
    void delete(Hospital hospital);

    @Query("SELECT * FROM hospitals WHERE id = :hospitalId") //Annotation untuk select
    Hospital load(Long hospitalId);

    @Query("SELECT * FROM hospitals") //Annotation untuk select all
    List<Hospital> loadAll();

    @Query("SELECT * FROM hospitals LIMIT :numOfData") //Annotation untuk select limit
    List<Hospital> loadList(Long numOfData);

    @Update //Annotation untuk update
    void save(Hospital hospital);

}
