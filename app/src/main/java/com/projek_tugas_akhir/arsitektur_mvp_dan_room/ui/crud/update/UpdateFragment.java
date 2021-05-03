package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.update;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpView;

import java.util.List;

import javax.inject.Inject;

public class UpdateFragment extends BaseFragment implements CRUDMvpView, CRUDAdapter.Callback {

    private static final String TAG = "UpdateFragment";

    @Inject
    CRUDMvpPresenter<CRUDMvpView> mPresenter;

    @Inject
    CRUDAdapter mCrudAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    RecyclerView mRecyclerView;

    public static UpdateFragment newInstance() {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        this.mRecyclerView = view.findViewById(R.id.updateRecyclerView);
        this.mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.mRecyclerView.setLayoutManager(mLayoutManager);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecyclerView.setAdapter(mCrudAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            this.mPresenter.onAttach(this);
            this.mCrudAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void onEmptyViewRetryClick() {

    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        this.mCrudAdapter.updateItems(medicalList);
    }

    @Override
    public void onDestroyView() {
        this.mPresenter.onDetach();
        super.onDestroyView();
    }
}

