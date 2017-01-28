package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        LinkButtons();
        ReadData();
    }

    private void ReadData()
    {
        Serialization.readSerializable(getApplicationContext());
        //TODO: try to log in.
    }

    private void LinkButtons()
    {
        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button login_button = (Button) findViewById(R.id.login_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitCode();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartUp.this, LoginActivity.class));
            }
        });
    }

    private void SubmitCode()
    {
        String input_code = ((EditText)findViewById(R.id.code_editText)).getText().toString();
        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        String FirstName = jsonResponse.getString("CID");
                        String LastName = jsonResponse.getString("SName");
                        String Email = jsonResponse.getString("CName");


                        Intent intent = new Intent(StartUp.this,CreateActivity.class);
                        intent.putExtra("CID",FirstName);
                        intent.putExtra("SName",LastName);
                        intent.putExtra("CName",Email);
                        StartUp.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
                        alert.setMessage("Cod inexistent").setNegativeButton("Inapoi",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Code_Request code_Request = new Code_Request(input_code,loginListener);
        RequestQueue code_Queue = Volley.newRequestQueue(StartUp.this);
        code_Queue.add(code_Request);
    }
}
