package com.example.oalex.carnetvirtual;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serialization
{
    public static Serialization serialization;
    private static String fileName = "studentdata.srl";

    public String email;
    public String password;

    public Serialization(String email, String password)
    {
        this.email = email;
        this.password = password;

    }

    public static void saveSerializable(Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(serialization);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readSerializable(Context context)
    {

        try
        {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serialization = (Serialization) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
