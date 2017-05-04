package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AbsenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medii_layout);

        ArrayList<String> materii = new ArrayList<>();
        for (Presences p: Student.student.presences)
            if(!materii.contains(p.materie))
                materii.add(p.materie);

        ListView absenteMaterii = (ListView) findViewById(R.id.materii_list);
        AbsenteAdapter adapter = new AbsenteAdapter(AbsenteActivity.this, R.layout.absente_adapter_layout, materii);
        absenteMaterii.setAdapter(adapter);
    }
}
