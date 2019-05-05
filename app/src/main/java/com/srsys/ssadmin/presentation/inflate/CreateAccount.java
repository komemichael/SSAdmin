package com.srsys.ssadmin.presentation.inflate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessUsers;

import static android.support.constraint.Constraints.TAG;

public class CreateAccount extends Activity
{
    EditText username, password, email;
    AccessUsers accessUsers;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflate_create_account);

        mAuth = FirebaseAuth.getInstance();

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
        configureAddBillButton();
    }


    public void updateUI(FirebaseUser mUser, String message)
    {
        if (mUser != null)
        {
            Toast.makeText(getApplicationContext(),
                    "Success!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void configureAddBillButton()
    {
        username = findViewById(R.id.editText_username_createaccount);
        password = findViewById(R.id.editText_password_createaccountt);
        email = findViewById(R.id.editText_email_createaccount);

        Button addAccountButton = findViewById(R.id.add_account_button_btn);

        addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username_String = username.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String email_add = email.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email_add, pass)
                    .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                accessUsers = new AccessUsers();
                                String errorMsg = accessUsers.addUser(username_String, pass, email_add);
                                if (errorMsg.equals(""))
                                {
                                    updateUI(user, "createUserWithEmail:success");
                                }
                                else
                                {
                                    updateUI(null, errorMsg);
                                }
                            } else {
                                updateUI(null, "createUserWithEmail:failure"+ task.getException());
                            }
                        }
                    });

                }
            });
    }
}

