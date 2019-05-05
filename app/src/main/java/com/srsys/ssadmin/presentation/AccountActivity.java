package com.srsys.ssadmin.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.presentation.Adapters.SectionsPagerAdapter;
import com.srsys.ssadmin.presentation.fragments.AccountMainFragment;
import com.srsys.ssadmin.presentation.fragments.AccountMessageFragment;

public class AccountActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(
                this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        adapter.addFragment(new AccountMainFragment(), "Home");
        adapter.addFragment(new AccountMessageFragment(), "Message");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
