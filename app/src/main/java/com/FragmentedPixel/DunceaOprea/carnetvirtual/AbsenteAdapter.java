package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oalex on 2017-05-04 .
 */

public class AbsenteAdapter extends ArrayAdapter<String>
{
    private ArrayList<String> objects;

    AbsenteAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.absente_adapter_layout, null);
        }

        String selSbj = objects.get(position);

        if (selSbj != null)
        {
            TextView materie = (TextView) v.findViewById(R.id.materia_textView);
            TextView abs_t = (TextView) v.findViewById(R.id.abs_textView);
            TextView absne_t = (TextView) v.findViewById(R.id.absne_textView);

            //Afisare materie
            materie.setText(selSbj);

            //Store data
            ArrayList<Presences> sbjPres = new ArrayList<>();
            //Read Data


            for (Presences pr : Student.student.presences)
                if (pr.materie.equalsIgnoreCase(selSbj))
                    sbjPres.add(pr);

            int abs = 0;
            int absne = 0;

            for (Presences pr : sbjPres)
            {
                abs ++;
                if(!pr.value)
                    absne++;
            }

            abs_t.setText("" + abs);
            absne_t.setText(""+absne);
        }


        return v;

    }


}
