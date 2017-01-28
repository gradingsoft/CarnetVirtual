package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class PresenceActivity extends AppCompatActivity {

    private TextView infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        Links();
    }

    private void Links()
    {
        infos = (TextView) findViewById(R.id.infos_textView);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                DataChanged(year, month, dayOfMonth);
            }
        });
    }

    private void DataChanged(int year, int month, int dayOfMonth)
    {
        infos.setText("Anul: " + year + "Luna: " + month + "Ziua: "+ dayOfMonth);
        //TODO: ask Duncea, facem tot cu request la server?

    }
}
