package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        PopulateDetails();

        Button logoutButton = (Button) findViewById(R.id.log_out_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Serialization.serialization = null;
                Serialization.saveSerializable(getApplicationContext());
                Intent intent = new Intent(FirstPage.this, StartUp.class);
                startActivity(intent);
            }
        });
    }

    private void PopulateDetails()
    {
        Student student = Student.student;

        ImageView studentimage = (ImageView) findViewById(R.id.studentpicture);
        studentimage.setImageBitmap(student.photo);

        TextView schoolname = (TextView) findViewById(R.id.schoolname_textView);
        schoolname.setText(student.schoolname);

        TextView schooladress = (TextView) findViewById(R.id.schooladresa_textView);
        schooladress.setText(student.schooladress);

        TextView schoolphone = (TextView) findViewById(R.id.schoolphone_textView);
        schoolphone.setText(student.schoolphone);

        TextView studentclass = (TextView) findViewById(R.id.studentclass_textView);
        studentclass.setText(student.className);

        TextView studentname = (TextView) findViewById(R.id.studentname_textView);
        studentname.setText(student.name);

        TextView studentforename = (TextView) findViewById(R.id.studentforename_textView);
        studentforename.setText(student.forename);

        TextView numarmatricol = (TextView) findViewById(R.id.nrmatricol_textView);
        numarmatricol.setText(student.serialNumber);

        TextView studentadress = (TextView) findViewById(R.id.studentadress_textView);
        studentadress.setText(student.adress);

        TextView studentphone = (TextView) findViewById(R.id.studentphone_textView);
        studentphone.setText(student.phone);
    }
}
