package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface InsertMvpView extends MvpView {

    void updateNumOfRecordInsert(Long numOfRecord);

    void updateInsertDatabaseTime(Long insertDatabaseTime);

    void updateAllInsertTime(Long allInsertTime);

    void updateViewInsertTime(Long viewInsertTime);

    void insertMedicalData(List<Medical> medicalList);

    void stateLoadingInsert(boolean state);

}
