package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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

        ArrayList<String> messages = new ArrayList<>();

        for (ChatMessage message: Student.student.chatMessages)
            messages.add(message.autor + ": " + message.message);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messagesListView.setAdapter(adapter);

    }
}
