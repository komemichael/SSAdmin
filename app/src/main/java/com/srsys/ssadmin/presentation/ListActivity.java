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
import android.widget.TextView;
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

        accessBills = new AccessBills();
        bills = accessBills.getBills();

        recyclerView = findViewById(R.id.list_recylerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        createOnclick();
        configureBackButton();

        mAdapter = new BillAdapter(bills);
        recyclerView.setAdapter(mAdapter);
    }


    public void configureBackButton()
    {
        Button backbutton = findViewById(R.id.bill_list_back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void createOnclick()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bills bill = bills.get(position);
                Toast.makeText(getApplicationContext(),bill.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) 
            {
                View popupView = getLayoutInflater().inflate(R.layout.inflate_display_bill_item, null);

                TextView billname = popupView.findViewById(R.id.display_billname);
                TextView billamnt = popupView.findViewById(R.id.display_billamnt);
                TextView billdate = popupView.findViewById(R.id.display_billdate);

                Bills bill = accessBills.getBills().get(position);

                billname.setText(bill.getName());
                billamnt.setText(bill.getAmount());
                billdate.setText(bill.getDate());

                final PopupWindow popupWindow = new PopupWindow(popupView, 1000,1000);
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
