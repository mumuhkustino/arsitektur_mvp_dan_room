package com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.PerActivity;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.module.ActivityModule;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDActivity;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete.DeleteFragment;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert.InsertFragment;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select.SelectFragment;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update.UpdateFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CRUDActivity activity);

    void inject(InsertFragment fragment);

    void inject(SelectFragment fragment);

    void inject(UpdateFragment fragment);

    void inject(DeleteFragment fragment);

}
