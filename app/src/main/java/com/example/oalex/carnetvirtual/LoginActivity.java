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
    private EditText mEmailView;
    private EditText mPasswordView;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.email);
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
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                            alert.setMessage("Email-ul e gresit.").setNegativeButton("Inapoi",null).create().show();
                        }
                        else if (!is_password_right)
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                            alert.setMessage("Parola e necorespunzatoare.").setNegativeButton("Inapoi",null).create().show();
                        }
                        else
                        {

                            String SName = jsonResponse.getString("SName");
                            String SAddress = jsonResponse.getString("SAddress");
                            String SPhone = jsonResponse.getString("SPhone");
                            String CName = jsonResponse.getString("CName");
                            String STName = jsonResponse.getString("STName");
                            String STFirstName = jsonResponse.getString("STFirstName");
                            //String STEmail = jsonResponse.getString("STEmail");
                            String STSerialNr = jsonResponse.getString("STSerialNr");
                            //String STCnp = jsonResponse.getString("STCnp");
                            String STAddress = jsonResponse.getString("STAddress");
                            String STPhone = jsonResponse.getString("STPhone");

                            new Student(SName,SAddress,SPhone,CName,STName,STFirstName,null,mEmail,mPassword,STSerialNr,null,STAddress,STPhone);
                            startActivity(new Intent(LoginActivity.this, Main.class));
                        }

                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                        alert.setMessage("Ups.. S-a intamplat ceva neprevazut").setNegativeButton("Inapoi",null).create().show();
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

