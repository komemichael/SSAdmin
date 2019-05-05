package com.srsys.ssadmin.presentation.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessMessages;
import com.srsys.ssadmin.objects.Message;
import com.srsys.ssadmin.presentation.Adapters.MessageAdapter;
import com.srsys.ssadmin.presentation.clickListeners.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class AccountMessageFragment extends Fragment
{
    private View view;
    private List<Message> messages;
    private AccessMessages accessMessages;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_account_message, container, false);

        recyclerView = view.findViewById(R.id.account_message_rv);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));

        createOnclick();

        accessMessages = new AccessMessages();
//        messages = accessMessages.getMessages();
        messages = new ArrayList<>();

        mAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public void createOnclick()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Bills bill = bills.get(position);
//                Toast.makeText(getContext(),bill.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position)
            {
                View popupView = getLayoutInflater().inflate(R.layout.inflate_display_bill_item, null);


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
