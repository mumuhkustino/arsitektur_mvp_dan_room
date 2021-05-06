package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.select;

import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpPresenter;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud.CRUDMvpView;

import java.util.List;

import javax.inject.Inject;

public class SelectFragment extends BaseFragment implements CRUDMvpView, CRUDAdapter.Callback {

    private static final String TAG = "SelectFragment";

    @Inject
    CRUDMvpPresenter<CRUDMvpView> mPresenter;

    @Inject
    SelectAdapter mSelectAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    ContentLoadingProgressBar progressBar;

    RecyclerView mRecyclerView;

    TextView mNumOfRecord;

    TextView mExecutionTime;

    EditText mEditTextNumData;

    Button btnExecute;

    public static SelectFragment newInstance() {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        this.mRecyclerView = view.findViewById(R.id.selectRecyclerView);
        this.mNumOfRecord = view.findViewById(R.id.textViewRecord);
        this.mExecutionTime = view.findViewById(R.id.textViewTime);
        this.mEditTextNumData = view.findViewById(R.id.editTextNumData);
        this.btnExecute = view.findViewById(R.id.btnExecute);
        this.progressBar = view.findViewById(R.id.progressBar);

        this.btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextNumData.getText() != null) {
                    try {
                        Long numOfData = Long.valueOf(mEditTextNumData.getText().toString());
                        mPresenter.onSelectExecuteClick(numOfData);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "onClick: ");
                    Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.mRecyclerView.setLayoutManager(mLayoutManager);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecyclerView.setAdapter(mSelectAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            this.mPresenter.onAttach(this);
            this.mSelectAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void onEmptyViewRetryClick() {

    }

    @Override
    public void updateNumOfRecord(Long numOfRecord) {
        this.mNumOfRecord.setText("RECORD : " + numOfRecord.toString());
    }

    @Override
    public void updateExecutionTime(Long executionTime) {
        this.mExecutionTime.setText("TIME (MS) : " + executionTime.toString());
    }

    @Override
    public void crudMedicalData(List<Medical> medicalList) {
        this.mSelectAdapter.crudItems(medicalList);
    }

    @Override
    public void stateLoading(boolean state) {
        if (state)
            this.progressBar.setVisibility(View.VISIBLE);
        else
            this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        this.mPresenter.onDetach();
        super.onDestroyView();
    }
}
