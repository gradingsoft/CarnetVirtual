package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MedieActivity extends AppCompatActivity {

    private String selSbj;
    private ArrayList<Grades> sbjGrades = new ArrayList<>();
    private boolean isTermPaper;
    private int gradeTermPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medii_layout);

        if(Student.student.selSbj != null)
            selSbj = Student.student.selSbj;
        else
            selSbj = Student.student.grades.get(0).materie;

        GetData();
        OnLoad();
    }

    private void GetData() {

        for (Grades g : Student.student.grades)
            if (g.materie.equalsIgnoreCase(selSbj))
                sbjGrades.add(g);

        for (Grades g : sbjGrades)
            if (g.isTermPaper) {
                isTermPaper = true;
                gradeTermPaper = g.value;
            }
    }

    private void OnLoad()
    {

        TextView materie = (TextView) findViewById(R.id.materia_textView);
        TextView teza = (TextView) findViewById(R.id.teza_textView);
        TextView note = (TextView) findViewById(R.id.note_textView);
        TextView medie = (TextView) findViewById(R.id.media_textView);

        //Afisare materie
        materie.setText(selSbj);

        //Afisare teza
        if(isTermPaper)
            teza.setText("Teza: " + String.valueOf(gradeTermPaper));
        else
            teza.setText("Teza: -");

        //Afisare note
        note.setText("");
        for (Grades g: sbjGrades)
            if(!g.isTermPaper)
                note.setText(note.getText() + String.valueOf(g.value) + " ");

        //Caluclare medie
        float sum = 0;
        int count = 0;

        for (Grades g : sbjGrades)
            if(!g.isTermPaper) {
                sum += g.value;
                count ++ ;
            }

        sum = sum/count;

        if(isTermPaper)
            sum = (sum * 3 + gradeTermPaper) / 4;

        medie.setText("Medie: " + String.valueOf(sum));
    }


}
