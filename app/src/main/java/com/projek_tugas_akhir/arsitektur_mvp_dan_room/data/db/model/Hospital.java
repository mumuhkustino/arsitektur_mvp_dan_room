package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "hospitals")
public class Hospital {

    @Expose
    @PrimaryKey
    public Long id;

    @Expose
    @SerializedName("hospitalName")
    @ColumnInfo(name = "hospitalName")
    public String name;

}
