package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

import java.util.List;

public interface CRUDMvpView extends MvpView {

    void updateMedical(List<Medical> medicalList);

}
