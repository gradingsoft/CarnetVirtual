package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-28.
 */

class Presences
{
    public Date date;
    boolean value;
    String materie;

    Presences(Date date, boolean value, String materie)
    {
        this.date = date;
        this.value = value;
        this.materie = materie;

        Student.student.presences.add(this);
    }


}
