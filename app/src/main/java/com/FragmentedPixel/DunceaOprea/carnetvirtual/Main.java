package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkButtons();
        TextView text_text = (TextView) findViewById(R.id.text_test);
        text_text.setText("Buna ziua, " + Student.student.forename + "! bine ati revenit.");
       // Refresh.LogIn(getApplicationContext(),Serialization.serialization.email,Serialization.serialization.password);
    }
    @Override
    public void onStart(){
        super.onStart();
       // Refresh.LogIn(getApplicationContext(),Serialization.serialization.email,Serialization.serialization.password);

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

        int count = 0;

        for (ChatMessage m: Student.student.chatMessages)
        {
            if(m.type >= 2) //2 sau 3 = test sau teza. si ziua este azi && m.date == Calendar.getInstance().getTime())
                count++;
        }
        if(count != 0)
            chatbutton.setText(chatbutton.getText() + "(" + count + ")");

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
