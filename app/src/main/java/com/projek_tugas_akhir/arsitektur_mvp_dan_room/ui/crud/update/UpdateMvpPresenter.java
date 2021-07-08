package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.others.ExecutionTimePreference;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

public interface UpdateMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onUpdateExecuteClick(ExecutionTimePreference executionTimePreference, Long numOfData);

}
