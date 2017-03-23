package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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


    public void Notification()
    {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.unnamed)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setAutoCancel(true);
        // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, FirstPage.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(FirstPage.class);
        // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(812612,mBuilder.build());
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());

        for (ChatMessage m: Student.student.chatMessages)
        {
            if(m.type >= 2 && Objects.equals(sdf.format(m.expirationDate), sdf.format(new Date())))
                count++;
        }
        if(count != 0)
            chatbutton.setText(chatbutton.getText() + "(" + count + ")");

        Button gradesbutton = (Button) findViewById(R.id.grades_button);
        gradesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification();
                Intent intent = new Intent(Main.this, GradesActivity.class);
                startActivity(intent);
            }
        });

        Button firstpagebutton = (Button) findViewById(R.id.firstpage_button);
        firstpagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Main.this, FirstPage.class);
                Intent intent = new Intent(Main.this, MedieActivity.class);
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
