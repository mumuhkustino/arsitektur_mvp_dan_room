package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface UpdateMvpView extends MvpView {

    void updateNumOfRecordUpdate(Long numOfRecord);

    void updateUpdateDatabaseTime(Long updateDatabaseTime);

    void updateAllUpdateTime(Long allUpdateTime);

    void updateViewUpdateTime(Long viewUpdateTime);

    void updateMedicalData(List<Medical> medicalList);

    void stateLoadingUpdate(boolean state);

}
