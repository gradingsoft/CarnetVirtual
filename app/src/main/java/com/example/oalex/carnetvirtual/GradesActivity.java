package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class GradesActivity extends AppCompatActivity {

    private Student student;

    private Spinner dropdownSpinner;
    private ListView gradesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        student = Student.student;
        gradesListView = (ListView) findViewById(R.id.grades_listView);
        dropdownSpinner = (Spinner) findViewById(R.id.materii_spinner);
        dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
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
        arraySpinner.add("");

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
            if(g.materie.equals(materie) || materie.isEmpty())
                gradesArrayList.add(g);

        PopulateListView(gradesArrayList);
    }

    private void PopulateListView(ArrayList<Grades> gradesList)
    {
        ArrayList<String> materii = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM", Locale.getDefault());

        for(Grades g : gradesList)
            materii.add(df.format(g.date) + " " + g.materie + " " + g.value);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, materii);
        gradesListView.setAdapter(adapter);

    }
}
