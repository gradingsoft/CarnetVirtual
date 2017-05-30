package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

class GradesAdapter extends ArrayAdapter<Grades>
{
    private ArrayList<Grades> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    GradesAdapter(Context context, int textViewResourceId, ArrayList<Grades> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        String toRoman[] ={"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"};
        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grades_item, null);
        }

		Grades g = objects.get(position);

        if (g != null)
        {
            TextView gValue = (TextView) v.findViewById(R.id.gradeValue);
            TextView gDate = (TextView) v.findViewById(R.id.gradedate);
            TextView gSubject = (TextView) v.findViewById(R.id.gradesubject);

            SimpleDateFormat df_day = new SimpleDateFormat("dd", Locale.getDefault());
            SimpleDateFormat df_month = new SimpleDateFormat("MM", Locale.getDefault());

            gValue.setText(""+g.value);
            ColorSet(gValue,g.value);

            gDate.setText(df_day.format(g.date)+" "+toRoman[Integer.parseInt(df_month.format(g.date))-1]);
            gSubject.setText(g.materie);
        }


        return v;

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ColorSet(TextView view, int grade)
    {

        if(grade<4)
            view.setBackground(getContext().getDrawable(R.drawable.background_red));
        else if(grade<9)
            view.setBackground(getContext().getDrawable(R.drawable.background_orange));
        else
            view.setBackground(getContext().getDrawable(R.drawable.background_green));
    }

}
