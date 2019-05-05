package com.srsys.ssadmin.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessLoggedIn;
import com.srsys.ssadmin.presentation.IntroActivity;

public class AccountMainFragment extends Fragment
{
    private View view;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_account_main, container, false);

        configureLogOutButton();

        return view;
    }

    public void configureLogOutButton()
    {
        Button logout_button = view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessLoggedIn accessLoggedIn = AccessLoggedIn.getInstance();
                accessLoggedIn.setLogged_in(false);
                accessLoggedIn.setPrefVariable("false");

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();
                        startActivity(new Intent(getContext(), IntroActivity.class));
                    }
                }, 1000);
            }
        });
    }
}