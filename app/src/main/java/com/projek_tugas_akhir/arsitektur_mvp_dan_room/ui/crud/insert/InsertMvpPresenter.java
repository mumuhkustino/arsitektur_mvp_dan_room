package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.MvpView;

public interface InsertMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onInsertExecuteClick(Long numOfData);

}
