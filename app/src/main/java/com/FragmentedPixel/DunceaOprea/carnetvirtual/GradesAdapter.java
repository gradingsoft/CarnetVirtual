package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GradesAdapter extends ArrayAdapter<Grades>
{
    private ArrayList<Grades> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public GradesAdapter(Context context, int textViewResourceId, ArrayList<Grades> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
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

            SimpleDateFormat df = new SimpleDateFormat("dd/MM", Locale.getDefault());
            gValue.setText(df.format(g.date));
            gDate.setText(String.valueOf(g.value));
            gSubject.setText(g.materie);
        }


        return v;

    }

}
