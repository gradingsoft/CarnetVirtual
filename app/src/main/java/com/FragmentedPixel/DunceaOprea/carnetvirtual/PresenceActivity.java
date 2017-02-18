package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class PresenceActivity extends AppCompatActivity
{

    private TextView infos;
    private String noPresencesText = "Nu aveti absente in aceasta zi.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        //Links();
        HashSet<Date> events = new HashSet<>();
        infos = (TextView) findViewById(R.id.infos_textView);

        int nepres = 0;
        int totpres=0;
        for (Presences p : Student.student.presences)
        {
            if(p.value)
            totpres++;
            else
            {
                nepres++;
                totpres++;
            }
            events.add(p.date);
        }
        CustomCalendarView cv = ((CustomCalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        infos.setText("Absente in total: " + totpres +"\nAbsente nemotivate: " + nepres );

        // assign event handler
        cv.setEventHandler(new CustomCalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                //DateFormat df = SimpleDateFormat.getDateInstance();
                //Toast.makeText(PresenceActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
            //s
            @Override
            public void onDayPress(Date date)
            {


                ArrayList<Presences> presences = Student.student.presences;
                ArrayList<Presences> dayPres = new ArrayList<>();
                for (Presences pres: presences)
                {

                    if(date.getYear() == pres.date.getYear() && date.getMonth() == pres.date.getMonth() && date.getDay() == pres.date.getDay())
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

        });
    }
/*
    private void Links()
    {


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

        //TODO: Remove this
        AlertDialog.Builder alert = new AlertDialog.Builder(PresenceActivity.this);
        if(prescenes_total != 0)
            alert.setMessage(infoText).setNegativeButton("Inapoi", null).create().show();
        else
            alert.setMessage(noPresencesText).setNegativeButton("Inapoi", null).create().show();
        //end Remove

        if(prescenes_total != 0)
            infos.setText(infoText);
        else
            infos.setText(noPresencesText);
    }*/
}
