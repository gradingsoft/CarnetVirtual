package com.example.oalex.carnetvirtual;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

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

        serialization = this;
    }

    public static void saveSerializable(Context context)
    {
        try
        {
            Toast.makeText(context, "a mers", Toast.LENGTH_LONG);

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
            Toast.makeText(context, "a mers", Toast.LENGTH_LONG);
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
