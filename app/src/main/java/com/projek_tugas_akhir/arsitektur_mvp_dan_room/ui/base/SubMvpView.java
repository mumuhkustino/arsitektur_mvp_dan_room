package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base;

public interface SubMvpView extends MvpView {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void attachParentMvpView(MvpView mvpView);

}
