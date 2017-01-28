package com.example.oalex.carnetvirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        PopulateDetails();
    }

    private void PopulateDetails()
    {
        Student student = Student.student;

        TextView schoolname = (TextView) findViewById(R.id.schoolname_textView);
        schoolname.setText(student.className);

        TextView schooladress = (TextView) findViewById(R.id.schooladresa_textView);
        schooladress.setText("Variabile pentru adresa scolii");

        TextView schoolphone = (TextView) findViewById(R.id.schoolphone_textView);
        schoolphone.setText("Variabile pentru telefonul scolii.");

        TextView studentname = (TextView) findViewById(R.id.studentname_textView);
        studentname.setText(student.name);

        TextView studentforename = (TextView) findViewById(R.id.studentforename_textView);
        studentforename.setText(student.forename);

        TextView studentadress = (TextView) findViewById(R.id.studentadress_textView);
        studentadress.setText(student.adress);

        TextView studentphone = (TextView) findViewById(R.id.studentphone_textView);
        studentphone.setText(student.phone);
    }
}
