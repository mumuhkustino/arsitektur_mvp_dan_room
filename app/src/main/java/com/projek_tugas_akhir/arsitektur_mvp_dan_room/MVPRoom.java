package com.projek_tugas_akhir.arsitektur_mvp_dan_room;

import android.app.Application;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.ApplicationComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.DaggerApplicationComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module.ApplicationModule;

public class MVPRoom extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
