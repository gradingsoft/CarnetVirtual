package com.FragmentedPixel.DunceaOprea.carnetvirtual;


import android.graphics.Bitmap;

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
    public Bitmap photo;
    public String e_mail;
    public String password;
    public String serialNumber;
    public Bitmap bsignature;
    public String adress;
    public String phone;

    public ArrayList<Grades> grades;
    public ArrayList<Presences> presences;
    public ArrayList<ChatMessage> chatMessages;

    public Student(String schoolname, String schooladress, String schoolphone,String className, String name, String forename,Bitmap photo, String e_mail, String password, Bitmap bsignature,String serialNumber, String adress, String phone)
    {
        this.schoolname = schoolname;
        this.schooladress = schooladress;
        this.schoolphone = schoolphone;

        this.className = className;
        this.name = name;
        this.forename = forename;
        this.photo = photo;
        this.bsignature = bsignature;
        this.e_mail = e_mail;
        this.password = password;
        this.serialNumber = serialNumber;
        this.adress = adress;
        this.phone = phone;

        new Serialization(this.e_mail, this.password);

        grades = new ArrayList<>();
        presences = new ArrayList<>();
        chatMessages = new ArrayList<>();

        student = this;
    }
}
