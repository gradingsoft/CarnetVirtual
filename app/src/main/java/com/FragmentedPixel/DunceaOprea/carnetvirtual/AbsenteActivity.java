package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class AbsenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
    }
}
