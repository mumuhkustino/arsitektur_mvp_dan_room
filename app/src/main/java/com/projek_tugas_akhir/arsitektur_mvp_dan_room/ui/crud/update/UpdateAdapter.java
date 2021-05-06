package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;

import java.util.List;

public class UpdateAdapter extends CRUDAdapter {

    private static final String TAG = UpdateAdapter.class.getSimpleName();

    public UpdateAdapter(List<Medical> medicalList) {
        super(medicalList);
    }

    @Override
    public void crudItems(List<Medical> medicalList) {
        super.clearItems();
        super.setMedicalList(medicalList);
        Log.d(TAG, "crudItems: " + medicalList.size());
    }
}
