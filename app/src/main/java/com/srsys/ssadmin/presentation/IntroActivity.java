package com.srsys.ssadmin.presentation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.srsys.ssadmin.BuildConfig;
import com.srsys.ssadmin.R;
import com.srsys.ssadmin.logic.AccessLoggedIn;
import com.srsys.ssadmin.presentation.inflate.CreateAccount;

public class IntroActivity extends Activity {

    public static final boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");
    private FirebaseAuth mAuth;
    LinearLayout linearLayout_welcome, linearLayout_login;
    EditText account_email;
    EditText account_password;

    Button createAccount_button, login_button;

    AccessLoggedIn accessLoggedIn;
    SharedPreferences mPrefs;
    String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        mPrefs = getSharedPreferences("LoggedIn", 0);
        accessLoggedIn  = AccessLoggedIn.getInstance();
        accessLoggedIn.setSharedPreference(mPrefs);

        configureAccountButton();
        configureLoginButton();
        configureForgotPassword();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null)
        {
            linearLayout_welcome = findViewById(R.id.login_linearlayout_welcome);
            linearLayout_login = findViewById(R.id.login_linearlayout_login);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    linearLayout_welcome.setVisibility(View.GONE);
                    linearLayout_login.setVisibility(View.VISIBLE);
                }
            }, 2000);
        }
        else {
            updateUI(currentUser, "");
        }
    }


    public void updateUI(FirebaseUser mUser, String message)
    {
        accessLoggedIn  = AccessLoggedIn.getInstance();

        if (mUser != null)
        {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            accessLoggedIn.setLogged_in(true);
            accessLoggedIn.setPrefVariable(mUser.getDisplayName());
            accessLoggedIn.setUid(mUser.getUid());

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent startActivity = new Intent(
                            IntroActivity.this, MainActivity.class);
                    startActivity(startActivity);
                    finish();
                }
            }, 800);
        }
    }

    private void configureAccountButton()
    {
        createAccount_button = findViewById(R.id.login_createaccount_button);
        createAccount_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, CreateAccount.class));
            }
        });
    }

    public void configureForgotPassword()
    {
        TextView forgot_pass_text = findViewById(R.id.login_forgot_password);

        forgot_pass_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout forgot_password_layout = findViewById(R.id.resetpass_layout);

                int visibility = forgot_password_layout.getVisibility();

                if (visibility == View.GONE)
                {
                    forgot_password_layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    forgot_password_layout.setVisibility(View.GONE);
                }

                Button reset_password_button = findViewById(R.id.send_reset_button);
                reset_password_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText reset_password = findViewById(R.id.reset_password_link);
                        String emailAddress = reset_password.getText().toString();

                        if (!emailAddress.isEmpty())
                        {
                            mAuth.sendPasswordResetEmail(emailAddress)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Reset Link sent", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Reset Link send failed!" +
                                                            task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Enter email address", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    private void configureLoginButton()
    {
        account_email = findViewById(R.id.editText_account_email);
        account_password = findViewById(R.id.editText_account_password);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email = account_email.getText().toString().trim();
            String password = account_password.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(IntroActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user, "signInWithEmail:success");
                    } else {
                        updateUI(null, "signInWithEmail:failure");
                    }
                    }
                });
            }
        });
    }
}
