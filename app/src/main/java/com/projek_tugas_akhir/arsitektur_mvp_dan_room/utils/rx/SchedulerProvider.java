package com.projek_tugas_akhir.arsitektur_mvp_dan_room.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

}
