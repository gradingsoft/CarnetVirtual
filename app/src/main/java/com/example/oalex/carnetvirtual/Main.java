package com.example.oalex.carnetvirtual;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Oprea stuff
        //new Student(1,"Clasa a XI-a", "Oprea","Alexandru", "dark_bubu@yahoo.com","1993003240016", "Calea dorobantilor 102-110","0757106250");
        LinkButtons();

        //Duncea stuff
        TextView text_text = (TextView) findViewById(R.id.text_test);
        text_text.setText("Buna ziua, " + Student.student.name + "! bine ati revenit.");
    }

    private void LinkButtons()
    {
        Button chatbutton = (Button) findViewById(R.id.chat_button);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        Button gradesbutton = (Button) findViewById(R.id.grades_button);
        gradesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, GradesActivity.class);
                startActivity(intent);
            }
        });

        Button firstpagebutton = (Button) findViewById(R.id.firstpage_button);
        firstpagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, FirstPage.class);
                startActivity(intent);
            }
        });

        Button presencebutton = (Button) findViewById(R.id.presence_button);
        presencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, PresenceActivity.class);
                startActivity(intent);
            }
        });

    }

}
