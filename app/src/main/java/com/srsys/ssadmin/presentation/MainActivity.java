package com.srsys.ssadmin.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.srsys.ssadmin.BuildConfig;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessBills;
import com.srsys.ssadmin.presentation.Adapters.HomeAdapter;
import com.srsys.ssadmin.presentation.inflate.AddBill;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private boolean firstLogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firstLogon = true;
        AccessBills accessBills = new AccessBills();

        configureRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser mUser)
    {
        if (mUser != null && firstLogon)
        {
            Toast.makeText(getApplicationContext(), "Welcome" +
                    mUser.getDisplayName(), Toast.LENGTH_LONG).show();
            firstLogon = false;
        }
    }

    public void configureRecyclerView()
    {
        recyclerView = findViewById(R.id.main_activity_recycler_view);
        List<Bitmap> logos = new ArrayList<>();
        List<String> name = new ArrayList<>();

        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_home_black_24dp));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_group_black_24dp));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_grid_on_black_24dp));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_playlist_add_check_black_24dp));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_perm_device_information_black_24dp));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.common_google_signin_btn_icon_dark_normal));
        logos.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_local_atm_black_24dp));

        name.add("Home");
        name.add("Account");
        name.add("Events");
        name.add("Chores");
        name.add("Message");
        name.add("IDK");
        name.add("Bills");

        HomeAdapter homeAdapter = new HomeAdapter(name, logos);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerView.setAdapter(homeAdapter);
    }
}
