package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ArrayList<String> messages = new ArrayList<>();

        for (ChatMessage message: Student.student.chatMessages)
            messages.add(getMessage(message));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messagesListView.setAdapter(adapter);
    }

    private String getMessage(ChatMessage m)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());

        String mesaj = m.autor + ":\n";
        mesaj += GetType(m.type) + " " + simpleDateFormat.format(m.date) + "\n";
        mesaj += m.message;

        return  mesaj;
    }

    private String GetType (int type)
    {
        if(type == 1)
            return "Mesaj";
        else if(type == 2)
            return  "Test";
        else
            return  "Teza";
    }
}
