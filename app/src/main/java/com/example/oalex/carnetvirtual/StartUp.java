package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartUp extends AppCompatActivity {

    private String[] codes;
    private String input_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        GetCodes();
        LinkButtons();
    }

    private void GetCodes()
    {
        codes = new String[4];
        codes[0] = "code1";
        codes[1] = "code2";
        codes[2] = "code3";
        codes[3] = "code4";
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
                Login();
            }
        });
    }

    private void SubmitCode()
    {
        input_code = ((EditText)findViewById(R.id.code_editText)).getText().toString();
        boolean found_code = false;

        for (int i = 0; i < codes.length; i++)
            if(input_code.equals(codes[i]))
            {
                found_code = true;
                i = codes.length;
            }

        if(found_code)
            startActivity(new Intent(this, CreateActivity.class));
        else
            Toast.makeText(this, "Code doesn't exist", Toast.LENGTH_SHORT).show();
    }

    private void Login()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
