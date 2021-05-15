package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

public interface SelectMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onSelectExecuteClick(Long numOfData);

}
