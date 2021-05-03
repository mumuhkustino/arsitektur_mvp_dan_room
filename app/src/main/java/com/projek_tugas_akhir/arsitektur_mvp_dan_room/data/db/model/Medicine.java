package com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(
        tableName = "medicine",
        foreignKeys = @ForeignKey(
                entity = Hospital.class,
                parentColumns = "id",
                childColumns = "hospitalId"
        )
)
public class Medicine {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    public Long id;

    @Expose
    @SerializedName("hospitalId")
    @ColumnInfo(name = "hospitalId")
    public Long hospitalId;

    @Expose
    @SerializedName("medicineName")
    @ColumnInfo(name = "name")
    public String name;

}
