package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class AbsenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int abs = 0;
        int absne = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absente);

        ArrayList<String> materii = new ArrayList<>();
        for (Presences p: Student.student.presences)
            if(!materii.contains(p.materie))
                materii.add(p.materie);

        Collections.sort(materii);

        ListView absenteMaterii = (ListView) findViewById(R.id.materii_list);
        AbsenteAdapter adapter = new AbsenteAdapter(AbsenteActivity.this, R.layout.absente_adapter_layout, materii);
        absenteMaterii.setAdapter(adapter);

        for (Presences pr : Student.student.presences)
        {
                abs ++;
                if(!pr.value)
                    absne++;
        }
        TextView AbsNe = (TextView) findViewById(R.id.absne_textView);
        TextView Abs = (TextView) findViewById(R.id.abs_textView);
        AbsNe.setText("Nemotivate ("+absne +")");
        Abs.setText("Total("+abs+")");
    }
}
