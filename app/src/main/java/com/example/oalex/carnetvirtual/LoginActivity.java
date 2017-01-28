package com.example.oalex.carnetvirtual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity
{
    private String[] e_mails;
    private String[] passwords;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ReadFromDB();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmail = mEmailView.getText().toString();
        mPassword = mPasswordView.getText().toString();
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
        //TODO: Add server request here or in login.
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

        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    boolean is_email_right = jsonResponse.getBoolean("is_email_right");
                    boolean is_password_right = jsonResponse.getBoolean("is_password_right");
                    if(success){

                        if (!is_email_right)
                            Toast.makeText(LoginActivity.this, "Wrong e-mail.", Toast.LENGTH_SHORT).show();
                        else if (!is_password_right)
                            Toast.makeText(LoginActivity.this, "Wrong password.", Toast.LENGTH_SHORT).show();
                        else
                        {
                            String STID = jsonResponse.getString("STID");
                            new Student();
                            startActivity(new Intent(LoginActivity.this, Main.class));
                        }

                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                        alert.setMessage("Ups.. S-a intamplat ceva neprevazut").setNegativeButton("1napoi",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Login_Request login_Request = new Login_Request (mEmail,mPassword,loginListener);
        RequestQueue login_Queue = Volley.newRequestQueue(LoginActivity.this);
        login_Queue.add(login_Request);


    }
}

