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

        ArrayList<String> messages = new ArrayList<>();

        for (ChatMessage message: Student.student.chatMessages)
            messages.add(getMessage(message));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messagesListView.setAdapter(adapter);


        for (int i = 0; i < adapter.getCount(); i++)
        {
            View currentLine = adapter.getView(i, messagesListView.getChildAt(i), messagesListView);
            currentLine.setBackgroundColor(Color.RED);
        }
    }

    private String getMessage(ChatMessage m)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());

        String mesaj =  GetType(m.type) + " " + m.autor + " la " + simpleDateFormat.format(m.date) + ":\n";
        mesaj +=  "\n";
        mesaj += m.message;


        return  mesaj;
    }

    private String GetType (int type)
    {
        if(type == 1)
            return "✉ Mesaj";
        else if(type == 2)
            return  "☞ Test";
        else
            return  "⌲ Teza";
    }

}
