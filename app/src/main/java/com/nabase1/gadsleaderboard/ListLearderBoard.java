package com.nabase1.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.nabase1.gadsleaderboard.adapters.PageAdapter;
import com.nabase1.gadsleaderboard.databinding.ActivityListLearderBoardBinding;
import com.nabase1.gadsleaderboard.fragments.LearningLeaders;

public class ListLearderBoard extends AppCompatActivity {

    private PageAdapter mPageAdapter;
    private ActivityListLearderBoardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_learder_board);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_learder_board);

        androidx.appcompat.widget.Toolbar toolbar1 = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar1);

        mPageAdapter = new PageAdapter(this);

        mBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mBinding.tabLayout.selectTab(mBinding.tabLayout.getTabAt(position));
            }
        });


        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mBinding.viewPager.setCurrentItem(tab.getPosition());

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
    protected void onResume() {
        super.onResume();
        mBinding.viewPager.setAdapter(mPageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public void submitForm(View view){
        startActivity(new Intent(this, SubmitProject.class));
    }
}