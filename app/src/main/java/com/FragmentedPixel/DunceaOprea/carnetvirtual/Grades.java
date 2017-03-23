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
    boolean isTermPaper;

    Grades(Date date, int value, String materie, boolean isTermPaper)
    {
        this.date = date;
        this.value = value;
        this.materie = materie;
        this.isTermPaper = isTermPaper;

        Student.student.grades.add(this);
    }
}
