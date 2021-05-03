package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base;

import androidx.annotation.StringRes;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void hideKeyboard();

}
