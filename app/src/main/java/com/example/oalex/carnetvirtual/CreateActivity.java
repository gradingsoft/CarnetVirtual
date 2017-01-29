package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //TODO: change clasa_editText.setText("XI G");

        EditText clasa_editText = (EditText) findViewById(R.id.clasa_editText);
        clasa_editText.setText("XI G");
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
        ArrayList<EditText> fields = new ArrayList<>();

        EditText name = (EditText) findViewById(R.id.name_editText);
        EditText forename = (EditText) findViewById(R.id.forname_editText);
        EditText email = (EditText) findViewById(R.id.email_editText);
        EditText cnp = (EditText) findViewById(R.id.cnp_editText);
        EditText phone_number = (EditText) findViewById(R.id.phone_editText);
        EditText pass1 = (EditText) findViewById(R.id.pass1_editText);
        EditText pass2 = (EditText) findViewById(R.id.pass2_editText);
        EditText adress = (EditText) findViewById(R.id.adress_editText);

        fields.add(name);
        fields.add(forename);
        fields.add(email);
        fields.add(cnp);
        fields.add(phone_number);
        fields.add(pass1);
        fields.add(pass2);
        fields.add(adress);

        for (EditText field: fields)
            if (field.getText().toString().equals("")) {
                Toast.makeText(this, "Toate campurile trebuie completate.", Toast.LENGTH_SHORT).show();
                return;
            }


        if(!email.getText().toString().contains("@"))
        {
            Toast.makeText(this, "E-mailul nu contine simbolul '@'.", Toast.LENGTH_SHORT).show();
            return;
        }

         if(! pass1.getText().toString().equals(pass2.getText().toString()))
        {
            Toast.makeText(this, "Parolele nu sunt identice.", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO: Add sutdent logic here: Student student = new Student(...);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
