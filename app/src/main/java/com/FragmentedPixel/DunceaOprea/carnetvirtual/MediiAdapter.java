package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oalex on 2017-03-23 .
 */

public class MediiAdapter extends ArrayAdapter<String>
{
    private ArrayList<String> objects;

    MediiAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.medii_adapter_layout, null);
        }

        String selSbj = objects.get(position);

        if (selSbj != null)
        {
            TextView materie = (TextView) v.findViewById(R.id.materia_textView);
            TextView teza = (TextView) v.findViewById(R.id.PresenceNumber_textView);
            TextView note = (TextView) v.findViewById(R.id.note_textView);
            TextView medie = (TextView) v.findViewById(R.id.media_textView);

            //Afisare materie
            materie.setText(selSbj);

            //Store data
            ArrayList<Grades> sbjGrades = new ArrayList<>();
            boolean isTermPaper = false;
            int gradeTermPaper = 0;

            //Read Data
            for (Grades gr : Student.student.grades)
                if (gr.materie.equalsIgnoreCase(selSbj))
                    sbjGrades.add(gr);

            for (Grades gr : sbjGrades)
                if (gr.isTermPaper) {
                    isTermPaper = true;
                    gradeTermPaper = gr.value;
                }

            //Afisare teza
            if(isTermPaper)
            {
                teza.setText(String.valueOf(gradeTermPaper));
                ColorSet(teza,gradeTermPaper);
            }
            else {
                teza.setText("-");
                teza.setBackground(null);
            }

            //Afisare note
            note.setText("");
            for (Grades gr: sbjGrades)
                if(!gr.isTermPaper)
                    note.setText(note.getText() + String.valueOf(gr.value) + " ");

            //Caluclare medie
            float sum = 0;
            int count = 0;

            for (Grades gr : sbjGrades)
                if(!gr.isTermPaper) {
                    sum += gr.value;
                    count ++ ;
                }

            sum = sum/count;
            sum = Math.round(sum);
            if(isTermPaper)
                sum = (sum * 3 + gradeTermPaper) / 4;
            medie.setText("Medie: " + String.valueOf(sum));
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
