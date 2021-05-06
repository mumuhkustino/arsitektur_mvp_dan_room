package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.insert;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;

import java.util.List;

public class InsertAdapter extends CRUDAdapter {

    private static final String TAG = InsertAdapter.class.getSimpleName();

    public InsertAdapter(List<Medical> medicalList) {
        super(medicalList);
    }

    @Override
    public void crudItems(List<Medical> medicalList) {
        super.clearItems();
        super.setMedicalList(medicalList);
        Log.d(TAG, "crudItems: " + medicalList.size());
    }
}
