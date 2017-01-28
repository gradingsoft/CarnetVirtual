package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_text = (TextView) findViewById(R.id.text_test);

        Intent intent = getIntent();
        String name = intent.getStringExtra("CID")+ " " +intent.getStringExtra("SName");
        String data = intent.getStringExtra("CName");

        text_text.setText(name+" "+data);
    }

}
