package com.example.oalex.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-28.
 */

public class Presences
{
    public Date date;
    public boolean value;
    public String materie;

    public Presences(Date date, boolean value, String materie)
    {
        this.date = date;
        this.value = value;
        this.materie = materie;

        Student.student.presences.add(this);
    }


}
