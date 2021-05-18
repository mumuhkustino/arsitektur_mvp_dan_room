package com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.AppDataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.AppDatabase;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.AppDbHelper;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.ApplicationContext;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.DatabaseInfo;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    @ApplicationContext
    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, @ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

}