package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ReadMessages();
    }

    private void ReadMessages()
    {
        ListView messagesListView = (ListView) findViewById(R.id.messages_listView);
        ChatAdapter adapter = new ChatAdapter(this, Student.student.chatMessages);
        messagesListView.setAdapter(adapter);
    }

}
