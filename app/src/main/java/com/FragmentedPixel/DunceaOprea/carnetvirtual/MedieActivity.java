package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medii_layout);

        ArrayList<String> materii = new ArrayList<>();
            for (Grades g: Student.student.grades)
                if(!materii.contains(g.materie))
                    materii.add(g.materie);

        ListView mediiMaterii = (ListView) findViewById(R.id.materii_list);
        MediiAdapter adapter = new MediiAdapter(MedieActivity.this, R.layout.medii_adapter_layout, materii);
        mediiMaterii.setAdapter(adapter);
    }
}
