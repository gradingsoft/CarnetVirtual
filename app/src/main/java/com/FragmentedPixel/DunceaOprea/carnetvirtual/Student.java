package com.FragmentedPixel.DunceaOprea.carnetvirtual;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Student
{
    public static Student student;

    String selSbj;

    String schoolname;
    String schooladress;
    String schoolphone;

    String className;
    public String name;
    String forename;
    Bitmap photo;
    private String e_mail;
    public String password;
    String serialNumber;
    Bitmap bsignature;
    String adress;
    String phone;

    ArrayList<Grades> grades;
    ArrayList<Presences> presences;
    ArrayList<ChatMessage> chatMessages;

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
