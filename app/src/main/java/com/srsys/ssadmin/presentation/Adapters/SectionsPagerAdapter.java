package com.srsys.ssadmin.presentation.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{

    private static final List<Fragment> fragments = new ArrayList<>();
    private static final List<String> titlelists = new ArrayList<>();
    private Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titlelists.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelists.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}