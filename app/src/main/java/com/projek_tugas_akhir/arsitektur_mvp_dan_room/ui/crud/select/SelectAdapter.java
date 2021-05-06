package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;

import java.util.List;

public class SelectAdapter extends CRUDAdapter {

    private static final String TAG = SelectAdapter.class.getSimpleName();

    public SelectAdapter(List<Medical> medicalList) {
        super(medicalList);
    }

    @Override
    public void crudItems(List<Medical> medicalList) {
        super.clearItems();
        super.setMedicalList(medicalList);
        Log.d(TAG, "crudItems: " + medicalList.size());
    }
}
