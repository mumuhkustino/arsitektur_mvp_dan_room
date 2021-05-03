package com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.crud;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.R;
import com.projek_tugas_akhir.arsitektur_mvp_dan_room.ui.base.BaseActivity;

import javax.inject.Inject;

public class CRUDActivity extends BaseActivity {

    @Inject
    CRUDMvpPresenter<CRUDMvpView> mPresenter;

    @Inject
    CRUDPagerAdapter mPagerAdapter;

    Toolbar mToolbar;

    ViewPager mViewPager;

    TabLayout mTabLayout;

//    public static Intent getStartIntent(Context context) {
//        Intent intent = new Intent(context, CRUDActivity.class);
//        return intent;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        getActivityComponent().inject(this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.mPagerAdapter.setCount(2);

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

}
