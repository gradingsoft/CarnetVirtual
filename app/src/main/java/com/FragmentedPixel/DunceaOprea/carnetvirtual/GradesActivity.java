package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GradesActivity extends AppCompatActivity {

    private Student student;
    private String toateMateriile = "Toate Materiile";

    private Spinner dropdownSpinner;
    private ListView gradesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        Button medie = (Button) findViewById(R.id.medie_button);
        medie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GradesActivity.this, MedieActivity.class);
                startActivity(i);
            }
        });

        student = Student.student;
        gradesListView = (ListView) findViewById(R.id.grades_listView);
        dropdownSpinner = (Spinner) findViewById(R.id.materii_spinner);
        dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Student.student.selSbj = dropdownSpinner.getSelectedItem().toString();
                Toast.makeText(GradesActivity.this, dropdownSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                FilterList(dropdownSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                FilterList("");
            }
        });

        PopulateDropDown();
    }

    private void PopulateDropDown()
    {
        ArrayList<String> arraySpinner = new ArrayList<>();
        arraySpinner.add(toateMateriile);

        for(Grades g: student.grades)
            if(!arraySpinner.contains(g.materie))
                arraySpinner.add(g.materie);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        dropdownSpinner.setAdapter(adapter);
    }

    private void FilterList(String materie)
    {
        ArrayList<Grades> gradesArrayList = new ArrayList<>();

        for(Grades g: student.grades)
            if(g.materie.equals(materie) || materie.equals(toateMateriile))
                gradesArrayList.add(g);

        GradesAdapter adapter = new GradesAdapter(GradesActivity.this, R.layout.grades_item, gradesArrayList);
        gradesListView.setAdapter(adapter);
    }

}
