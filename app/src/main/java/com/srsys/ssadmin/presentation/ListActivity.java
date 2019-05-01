package com.srsys.ssadmin.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessBills;
import com.srsys.ssadmin.objects.Bills;
import com.srsys.ssadmin.presentation.Adapters.BillAdapter;
import com.srsys.ssadmin.presentation.clickListeners.RecyclerTouchListener;

import java.util.List;

public class ListActivity extends Activity
{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private AccessBills accessBills;

    List<Bills> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.list_recylerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        accessBills = new AccessBills();
        bills = accessBills.getBills();

        mAdapter = new BillAdapter(bills);
        recyclerView.setAdapter(mAdapter);

        createOnclick();
    }


    public void createOnclick()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bills bill = bills.get(position);
                Toast.makeText(getApplicationContext(), bill.getNamebill() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) 
            {
                View popupView = getLayoutInflater().inflate(R.layout.inflate_add_bill, null);

                final EditText addbill_text = popupView.findViewById(R.id.editText_addBill);
                final Button addbill_btn = popupView.findViewById(R.id.add_bill_button);
                addbill_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name_bill = addbill_text.getText().toString();
                        accessBills.addBill(name_bill);
                    }
                });

                final PopupWindow popupWindow = new PopupWindow(popupView, 1000,1400);
                popupWindow.setAnimationStyle(R.style.popup_window_animation_phone);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.update();
                popupWindow.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
            }
        }));
    }

}
