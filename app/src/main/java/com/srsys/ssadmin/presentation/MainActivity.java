package com.srsys.ssadmin.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.srsys.ssadmin.BuildConfig;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessBills;
import com.srsys.ssadmin.presentation.inflate.AddBill;

public class MainActivity extends Activity {

    public static final boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");
    private Button account_button, addbill_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAccountButton();
        configureAddBillButton();
    }

    private void configureAccountButton()
    {
        account_button = findViewById(R.id.my_list_button);
        account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }

    private void configureAddBillButton()
    {
        addbill_button = findViewById(R.id.add_bills_button);
        addbill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddBill.class));
            }
        });
    }
}
