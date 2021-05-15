package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface SelectMvpView extends MvpView {

    void updateNumOfRecordSelect(Long numOfRecord);

    void updateExecutionTimeSelect(Long executionTime);

    void selectMedicalData(List<Medical> medicalList);

    void stateLoadingSelect(boolean state);

}
