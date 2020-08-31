package com.nabase1.gadsleaderboard.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nabase1.gadsleaderboard.fragments.LearningLeaders;
import com.nabase1.gadsleaderboard.fragments.SkillIQLeaders;

public class PageAdapter extends FragmentStateAdapter {

   private int numOfTabs;

    public PageAdapter(FragmentActivity fm){
        super(fm);
       // this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LearningLeaders();

            case 1:
                return new SkillIQLeaders();

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
