package com.srsys.ssadmin.presentation.inflate;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessBills;

public class AddBill extends Activity
{
    private AccessBills accessBills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflate_add_bill);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8), (int)(height*0.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);

        accessBills = new AccessBills();

        configureBtn();
    }

    private void configureBtn()
    {

        final EditText addbill_text = findViewById(R.id.editText_addBill);
        final Button addbill_btn = findViewById(R.id.add_bill_button_btn);
        addbill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_bill = addbill_text.getText().toString();

                if (accessBills.addBill(name_bill))
                {
                    finish();
                }
            }
        });
    }
}
