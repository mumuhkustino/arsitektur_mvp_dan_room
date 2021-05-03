package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

}
