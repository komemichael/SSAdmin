package com.srsys.ssadmin.presentation.inflate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessBills;
import com.srsys.ssadmin.presentation.utilities.SetDate;

import java.util.Calendar;

public class AddBill extends Activity
{
    private AccessBills accessBills;
    private final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    private EditText editTextFromDate;
    private EditText addbill_text;
    private EditText addbill_amount;
    private Spinner addbill_type;
    private Button addbill_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflate_add_bill);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.9), (int)(height*0.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -10;

        getWindow().setAttributes(params);

        accessBills = new AccessBills();

        configureAddBillButton();
        configureDatepicker();

    }

    private void configureDatepicker()
    {
        editTextFromDate = findViewById(R.id.editText_addBill_date);
        SetDate fromDate = new SetDate(editTextFromDate, this);
        fromDate.toString();
    }

    private void configureAddBillButton()
    {
        addbill_text = findViewById(R.id.editText_addBill);
        addbill_amount = findViewById(R.id.editText_addBill_amount);
        addbill_type = findViewById(R.id.addbilltype);
        addbill_btn = findViewById(R.id.add_bill_button_btn);

        addbill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_bill = addbill_text.getText().toString();
                String type_bill = String.valueOf(addbill_type.getSelectedItem());
                String date_bill = editTextFromDate.getText().toString();
                String amount_bill = addbill_amount.getText().toString();

                String error = accessBills.addBill(name_bill, date_bill, amount_bill, type_bill);

                if (error.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                    addbill_text.getText().clear();
                    addbill_amount.getText().clear();
                    editTextFromDate.getText().clear();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "   Failed!\n" + error, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
