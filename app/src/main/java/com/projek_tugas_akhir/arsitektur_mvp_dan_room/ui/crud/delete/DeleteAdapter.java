package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.delete;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;

import java.util.List;

public class DeleteAdapter extends CRUDAdapter {

    private static final String TAG = DeleteAdapter.class.getSimpleName();

    public DeleteAdapter(List<Medical> medicalList) {
        super(medicalList);
    }

    @Override
    public void crudItems(List<Medical> medicals) {
        super.clearItems();
        super.setMedicalList(medicals);
        Log.d(TAG, "crudItems: " + medicals.size());
    }
}
