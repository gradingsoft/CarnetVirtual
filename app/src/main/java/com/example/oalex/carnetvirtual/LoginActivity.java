package com.example.oalex.carnetvirtual;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity
{
    private String[] e_mails;
    private String[] passwords;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ReadFromDB();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });
    }

    private void ReadFromDB()
    {
        e_mails = new String[4];
        e_mails[0] = "florea@yahoo.com";
        e_mails[1] = "dark_bubu@yahoo.com";
        e_mails[2] = "duncea_vlad_31@yahoo.com";
        e_mails[3] = "mergi_pls@yahoo.com";

        passwords = new String[4];
        passwords[0] = "password1";
        passwords[1] = "password2";
        passwords[2] = "password3";
        passwords[3] = "password4";
    }

    private void LogIn() {
        Boolean is_email_right = false;
        Boolean is_password_right = false;

        String emailText = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        for (int i = 0; i < e_mails.length; i++)
            if (emailText.equalsIgnoreCase(e_mails[i]))
            {
                is_email_right = true;
                is_password_right = password.equals(passwords[i]);
                i = e_mails.length;
            }

        if (!is_email_right)
            Toast.makeText(this, "Wrong e-mail.", Toast.LENGTH_SHORT).show();
        else if (!is_password_right)
            Toast.makeText(this, "Wrong password.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_LONG).show();
    }
}

