package com.srsys.ssadmin.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.srsys.ssadmin.BuildConfig;
import com.srsys.ssadmin.R;

public class MainActivity extends AppCompatActivity {

    public static final boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAccountButton();
    }

    private void configureAccountButton()
    {
        Button account_button = findViewById(R.id.my_list_button);
        account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }
}
