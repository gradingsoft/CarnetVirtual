package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {

    private Student student;

    private Spinner dropdownSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        student = Student.student;
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

        //TODO: Remove this.
        new Grades(null, 10, "Geografie");
        new Grades(null, 10, "Mate");
        new Grades(null, 8, "Mate");
        new Grades(null, 10, "Iunia");
        //End Remove.

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

        //TODO: Remove this and populate listview.
        for(Grades g: gradesArrayList)
            Toast.makeText(GradesActivity.this, g.materie+ " " + g.value, Toast.LENGTH_SHORT).show();
    }
}
