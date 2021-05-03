package com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component;

import android.app.Application;
import android.content.Context;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.MVPRoom;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.ApplicationContext;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MVPRoom app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}
