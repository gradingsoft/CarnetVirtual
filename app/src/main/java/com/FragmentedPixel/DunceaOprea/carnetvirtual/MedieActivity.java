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

    private String selSbj;
    private ArrayList<Grades> sbjGrades = new ArrayList<>();
    private boolean isTermPaper;
    private int gradeTermPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medii_layout);


        if(!Student.student.selSbj.equals("Toate Materiile"))
            selSbj = Student.student.selSbj;
        else
            selSbj = Student.student.grades.get(0).materie;

        ArrayList<String> materii = new ArrayList<>();
            for (Grades g: Student.student.grades)
                if(!materii.contains(g.materie))
                    materii.add(g.materie);

        ListView mediiMaterii = (ListView) findViewById(R.id.materii_list);
        MediiAdapter adapter = new MediiAdapter(MedieActivity.this, R.layout.medii_adapter_layout, materii);
        mediiMaterii.setAdapter(adapter);
    }
}
