package com.example.oalex.carnetvirtual;

/**
 * Created by oalex on 2017-01-28.
 */

public class Student
{
    public static Student student;

    public String schoolname;
    public String schooladress;
    public String schoolphone;

    public String className;
    public String name;
    public String forename;
    public String e_mail;
    public String cnp;
    public String adress;
    public String phone;

    //TODO: ask duncea about serial number
    //public static String serial number

    public Student(String schoolname, String schooladress, String schoolphone,String className, String name, String forename, String e_mail, String cnp, String adress, String phone)
    {
        this.schoolname = schoolname;
        this.schooladress = schooladress;
        this.schoolphone = schoolphone;

        this.className = className;
        this.name = name;
        this.forename = forename;
        this.e_mail = e_mail;
        this.cnp = cnp;
        this.adress = adress;
        this.phone = phone;

        student = this;
    }


}
