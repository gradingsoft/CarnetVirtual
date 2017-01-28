package com.example.oalex.carnetvirtual;


import android.media.Image;

import java.util.ArrayList;

public class Student
{
    public static Student student;

    public String schoolname;
    public String schooladress;
    public String schoolphone;

    public String className;
    public String name;
    public String forename;
    public Image photo;
    public String e_mail;
    public String password;
    public String cnp;
    public String serialNumber;
    public String adress;
    public String phone;

    public ArrayList<Grades> grades;
    public ArrayList<Presences> presences;

    public Student(String schoolname, String schooladress, String schoolphone,String className, String name, String forename,Image photo, String e_mail, String password, String serialNumber, String cnp, String adress, String phone)
    {
        this.schoolname = schoolname;
        this.schooladress = schooladress;
        this.schoolphone = schoolphone;

        this.className = className;
        this.name = name;
        this.forename = forename;
        this.photo = photo;
        this.e_mail = e_mail;
        this.password = password;
        this.cnp = cnp;
        this.serialNumber = serialNumber;
        this.adress = adress;
        this.phone = phone;

        Serialization serialization = new Serialization(this.e_mail, this.password);

        grades = new ArrayList<>();
        presences = new ArrayList<>();
        student = this;
    }
}
