package com.example.oalex.carnetvirtual;


import android.media.Image;

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
    public String cnp;
    public String serialNumber;
    public String adress;
    public String phone;



    public Student(String schoolname, String schooladress, String schoolphone,String className, String name, String forename,Image photo, String e_mail, String serialNumber, String cnp, String adress, String phone)
    {
        this.schoolname = schoolname;
        this.schooladress = schooladress;
        this.schoolphone = schoolphone;

        this.className = className;
        this.name = name;
        this.forename = forename;
        this.photo = photo;
        this.e_mail = e_mail;
        this.cnp = cnp;
        this.serialNumber = serialNumber;
        this.adress = adress;
        this.phone = phone;

        student = this;
    }


}
