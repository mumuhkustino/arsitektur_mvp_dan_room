package com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.ActivityContext;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.PerActivity;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDPagerAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.AppSchedulerProvider;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    CRUDMvpPresenter<CRUDMvpView> provideCrudPresenter(
            CRUDPresenter<CRUDMvpView> presenter) {
        return presenter;
    }

    @Provides
    CRUDPagerAdapter provideCrudPagerAdapter(AppCompatActivity activity) {
        return new CRUDPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    CRUDAdapter provideCRUDAdapter() {
        return new CRUDAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
