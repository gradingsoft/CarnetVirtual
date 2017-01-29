package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class PresenceActivity extends AppCompatActivity {

    private TextView infos;
    private String noPresencesText = "Nu aveti absente in aceasta zi.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        //TODO: Remove this
        new Presences(Calendar.getInstance().getTime(), true, "Geografie");
        new Presences(Calendar.getInstance().getTime(), false, "Geografie");
        new Presences(Calendar.getInstance().getTime(), true, "Geografie1");

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
        ArrayList<Presences> presences = Student.student.presences;
        ArrayList<Presences> dayPres = new ArrayList<>();
        for (Presences pres: presences)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(pres.date);
            int cyear = cal.get(Calendar.YEAR);
            int cmonth = cal.get(Calendar.MONTH);
            int cday = cal.get(Calendar.DAY_OF_MONTH);

            if(cyear == year && cmonth == month && cday == dayOfMonth)
                dayPres.add(pres);

        }

        String infoText="Materii: ";
        int presences_number = 0;
        int prescenes_total = 0;
        for(Presences pres: dayPres)
        {
            prescenes_total += 1;
            if(!pres.value) {
                presences_number += 1;
                infoText += pres.materie + " ";
            }
        }
        infoText = "Absente nemotivate: " + presences_number + "\n" + infoText + "\n" + "Absente in total: " + prescenes_total;

        if(prescenes_total != 0)
            infos.setText(infoText);
        else
            infos.setText(noPresencesText);
    }
}
