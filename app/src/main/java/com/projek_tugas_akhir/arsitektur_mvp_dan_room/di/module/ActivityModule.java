package com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.ActivityContext;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDPagerAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete.DeleteAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete.DeleteMvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete.DeletePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert.InsertAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert.InsertMvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert.InsertPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select.SelectAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select.SelectMvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select.SelectPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update.UpdateAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update.UpdateMvpView;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update.UpdatePresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.AppSchedulerProvider;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import javax.inject.Named;

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
    @Named("insertPresenter")
    InsertPresenter<InsertMvpView> provideInsertPresenter(
            InsertPresenter<InsertMvpView> presenter) {
        return presenter;
    }

    @Provides
    @Named("selectPresenter")
    SelectPresenter<SelectMvpView> provideSelectPresenter(
            SelectPresenter<SelectMvpView> presenter) {
        return presenter;
    }

    @Provides
    @Named("updatePresenter")
    UpdatePresenter<UpdateMvpView> provideUpdatePresenter(
            UpdatePresenter<UpdateMvpView> presenter) {
        return presenter;
    }

    @Provides
    @Named("deletePresenter")
    DeletePresenter<DeleteMvpView> provideDeletePresenter(
            DeletePresenter<DeleteMvpView> presenter) {
        return presenter;
    }

    @Provides
    CRUDPagerAdapter provideCrudPagerAdapter(AppCompatActivity activity) {
        return new CRUDPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    @Named("insertAdapter")
    InsertAdapter provideInsertAdapter() {
        return new InsertAdapter(new ArrayList<>());
    }

    @Provides
    @Named("selectAdapter")
    SelectAdapter provideSelectAdapter() {
        return new SelectAdapter(new ArrayList<>());
    }

    @Provides
    @Named("updateAdapter")
    UpdateAdapter provideUpdateAdapter() {
        return new UpdateAdapter(new ArrayList<>());
    }

    @Provides
    @Named("deleteAdapter")
    DeleteAdapter provideDeleteAdapter() {
        return new DeleteAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
