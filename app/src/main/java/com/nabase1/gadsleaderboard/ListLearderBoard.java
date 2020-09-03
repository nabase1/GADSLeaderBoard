package com.nabase1.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
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
import com.nabase1.gadsleaderboard.fragments.LearningLeaders;

public class ListLearderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_learder_board);

        androidx.appcompat.widget.Toolbar toolbar1 = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar1);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem learner_item = findViewById(R.id.tab_learning_learders);
        TabItem iq_item = findViewById(R.id.tab_skill_iq);
        ViewPager2 viewPager = findViewById(R.id.view_pager);



        PageAdapter pageAdapter = new PageAdapter(this);
        viewPager.setAdapter(pageAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void initialize(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.item_submit){
//            startActivity(new Intent(this, SubmitProject.class));
//        }

        return super.onOptionsItemSelected(item);
    }

    public void submitForm(View view){
        startActivity(new Intent(this, SubmitProject.class));
    }
}