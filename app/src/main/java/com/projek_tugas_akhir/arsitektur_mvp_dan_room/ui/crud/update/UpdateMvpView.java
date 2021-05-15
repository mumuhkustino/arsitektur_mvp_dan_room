package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface UpdateMvpView extends MvpView {

    void updateNumOfRecordUpdate(Long numOfRecord);

    void updateExecutionTimeUpdate(Long executionTime);

    void updateMedicalData(List<Medical> medicalList);

    void stateLoadingUpdate(boolean state);

}
