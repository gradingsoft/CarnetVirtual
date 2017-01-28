package com.example.oalex.carnetvirtual;

/**
 * Created by oalex on 2017-01-28.
 */

public class Student
{
    public static Student student;

    public int CID;
    public String className;
    public String name;
    public String forename;
    public String e_mail;
    public long cnp;
    public String adress;
    public String phone;

    //TODO: ask duncea about serial number
    //public static String serial number

    public Student(int CID,String className, String name, String forename, String e_mail, long cnp, String adress, String phone)
    {
        this.CID = CID;
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
