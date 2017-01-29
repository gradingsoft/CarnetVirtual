package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    String Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //TODO: change clasa_editText.setText("XI G");

        Intent intent = getIntent();

        EditText clasa_editText = (EditText) findViewById(R.id.clasa_editText);
        Class = intent.getStringExtra("CName");
        clasa_editText.setText(Class);
        clasa_editText.setInputType(0);
        //EditText scoala_editText = (EditText) findViewById(R.id.clasa_editText);
        //clasa_editText.setText("XI G");
        //clasa_editText.setInputType(0);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckSubmit();
            }
        });
    }

    private void CheckSubmit()
    {
        EditText tname = (EditText) findViewById(R.id.name_editText);
        EditText tforename = (EditText) findViewById(R.id.forname_editText);
        EditText temail = (EditText) findViewById(R.id.email_editText);
        EditText tcnp = (EditText) findViewById(R.id.cnp_editText);
        EditText tphone_number = (EditText) findViewById(R.id.phone_editText);
        EditText tpass1 = (EditText) findViewById(R.id.pass1_editText);
        EditText tpass2 = (EditText) findViewById(R.id.pass2_editText);
        EditText taddress = (EditText) findViewById(R.id.adress_editText);

        String name        =tname.getText().toString();
        String forename    =tforename.getText().toString();
        String email       =temail.getText().toString();
        String cnp         =tcnp.getText().toString();
        String phone_number=tphone_number.getText().toString();
        String pass1       =tpass1.getText().toString();
        String pass2       =tpass2.getText().toString();
        String address    =taddress.getText().toString();

        ArrayList<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(forename);
        fields.add(email);
        fields.add(cnp);
        fields.add(phone_number);
        fields.add(pass1);
        fields.add(pass2);
        fields.add(address);

        for (String field: fields)
            if (field.equals("")) {
                Toast.makeText(this, "Toate campurile trebuie completate.", Toast.LENGTH_SHORT).show();
                return;
            }


        if(!email.contains("@"))
        {
            Toast.makeText(this, "E-mailul nu contine simbolul '@'.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(! pass1.equals(pass2))
        {
            Toast.makeText(this, "Parolele nu sunt identice.", Toast.LENGTH_SHORT).show();
            return;
        }

        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                        alert.setMessage("Ups.. S-a intamplat ceva neprevazut").setNegativeButton("Inapoi",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        _Register_Request register_Request = new _Register_Request(name,forename,email,cnp,phone_number,Class,pass1,address,loginListener);
        RequestQueue register_Queue = Volley.newRequestQueue(CreateActivity.this);
        register_Queue.add(register_Request);








        //TODO: Add sutdent logic here: Student student = new Student(...);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
