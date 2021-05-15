package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.MVPRoom;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.DaggerActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module.ActivityModule;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.CommonUtils;

public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MVPRoom) getApplication()).getComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return this.mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showLoading() {
        hideLoading();
        this.mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Log.d("BA", "showSnackBar: " + message);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected abstract void setUp();
}
