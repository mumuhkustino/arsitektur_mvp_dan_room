package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface DeleteMvpView extends MvpView {

    void updateNumOfRecordDelete(Long numOfRecord);

    void updateDeleteDatabaseTime(Long deleteDatabaseTime);

    void updateAllDeleteTime(Long allDeleteTime);

    void updateViewDeleteTime(Long viewDeleteTime);

    void deleteMedicalData(List<Medical> medicalList);

    void stateLoadingDelete(boolean state);

}
