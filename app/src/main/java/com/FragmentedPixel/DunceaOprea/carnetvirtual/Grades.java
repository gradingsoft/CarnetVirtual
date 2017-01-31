package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-28.
 */

public class Grades
{

    public Date date;
    public int value;
    public String materie;

    public Grades(Date date, int value, String materie)
    {
        this.date = date;
        this.value = value;
        this.materie = materie;

        Student.student.grades.add(this);
    }
}
