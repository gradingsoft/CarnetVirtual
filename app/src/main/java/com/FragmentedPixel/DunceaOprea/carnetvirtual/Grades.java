package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-28.
 */

class Grades
{

    public Date date;
    int value;
    String materie;

    Grades(Date date, int value, String materie)
    {
        this.date = date;
        this.value = value;
        this.materie = materie;

        Student.student.grades.add(this);
    }
}
