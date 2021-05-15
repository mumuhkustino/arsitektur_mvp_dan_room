package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

public interface DeleteMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onDeleteExecuteClick(Long numOfData);

}
