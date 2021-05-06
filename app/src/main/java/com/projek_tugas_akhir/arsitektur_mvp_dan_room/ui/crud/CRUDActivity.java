package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.data.db.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class CRUDActivity extends BaseActivity implements CRUDMvpView {

    @Inject
    CRUDMvpPresenter<CRUDMvpView> mPresenter;

    @Inject
    CRUDPagerAdapter mPagerAdapter;

    Toolbar mToolbar;

    ViewPager mViewPager;

    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        this.mToolbar = findViewById(R.id.toolbar);
        this.mViewPager = findViewById(R.id.crudViewPager);
        this.mTabLayout = findViewById(R.id.tabLayout);

        getActivityComponent().inject(this);
        setUp();
    }

    @Override
    protected void setUp() {
        mPagerAdapter.setCount(4);

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText("INSERT"));
        mTabLayout.addTab(mTabLayout.newTab().setText("SELECT"));
        mTabLayout.addTab(mTabLayout.newTab().setText("UPDATE"));
        mTabLayout.addTab(mTabLayout.newTab().setText("DELETE"));

        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void updateNumOfRecord(Long numOfRecord) {

    }

    @Override
    public void updateExecutionTime(Long executionTime) {

    }

    @Override
    public void crudMedicalData(List<Medical> medicalList) {

    }

    @Override
    public void stateLoading(boolean state) {

    }
}
