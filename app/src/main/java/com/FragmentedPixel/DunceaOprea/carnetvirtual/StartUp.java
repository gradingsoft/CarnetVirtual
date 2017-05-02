package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        LinkButtons();

        Serialization.readSerializable(getApplicationContext());
        if (Serialization.serialization != null)
            ProgressBar();
    }
    public ProgressDialog pglogin = null;
    private void ProgressBar()
    {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        // Test for connection
        if (netInfo!= null && netInfo.isConnectedOrConnecting()) {}
        else {

            AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
            alert.setMessage("Conexiune la internet inexistenta.").setNegativeButton("Inapoi",null).create().show();
            // No conection
            return;
        }

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(StartUp.this);
        progressDialog.setMessage("Va rugam asteptati.");
        progressDialog.setTitle("Incarcare date");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                pglogin=progressDialog;
                    LogIn();
            }
        }).start();
    }

    Integer versionCode = BuildConfig.VERSION_CODE;

    private void LogIn() {
        final String mEmail= Serialization.serialization.email;
        final String mPassword = Serialization.serialization.password;
        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    boolean is_email_right = jsonResponse.getBoolean("is_email_right");
                    boolean is_password_right = jsonResponse.getBoolean("is_password_right");
                    if(success){
                        boolean Version = jsonResponse.getBoolean("Version");
                        if (!Version) {
                            pglogin.dismiss();
                            AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
                            alert.setMessage("Versiune veche! Va rugam faceti update aplicatiei!").setNegativeButton("Inapoi", null).create().show();
                            return;
                        }

                        if (!is_email_right||!is_password_right)
                        {
                            pglogin.dismiss();
                            startActivity(new Intent(StartUp.this, LoginActivity.class));
                        }
                        else
                        {

                            String SName = jsonResponse.getString("SName");
                            String SAddress = jsonResponse.getString("SAddress");
                            String SPhone = jsonResponse.getString("SPhone");
                            String CName = jsonResponse.getString("CName");
                            String STName = jsonResponse.getString("STName");
                            String STFirstName = jsonResponse.getString("STFirstName");
                            String STSerialNr = jsonResponse.getString("STSerialNr");
                            String STAddress = jsonResponse.getString("STAddress");
                            String STPhone = jsonResponse.getString("STPhone");
                            Integer Grade_nr = jsonResponse.getInt("Grade_nr");
                            Integer Presence_nr = jsonResponse.getInt("Presence_nr");
                            Integer Chat_nr = jsonResponse.getInt("Chat_nr");

                            String STPicture = jsonResponse.getString("STPicture");
                            byte[] byteArray_st = STPicture.getBytes("UTF-16");
                            byte[] stpicture = Base64.decode(byteArray_st, Base64.DEFAULT);
                            Bitmap STPicture_bm = BitmapFactory.decodeByteArray(stpicture, 0 ,stpicture.length);

                            String BSignature = jsonResponse.getString("BSignature");
                            byte[] byteArray_bs = BSignature.getBytes("UTF-16");
                            byte[] bsignature = Base64.decode(byteArray_bs, Base64.DEFAULT);
                            Bitmap BSignature_bm = BitmapFactory.decodeByteArray(bsignature, 0 ,bsignature.length);

                            new Student(SName,SAddress,SPhone,CName,STName,STFirstName,STPicture_bm,mEmail, mPassword, BSignature_bm,STSerialNr,STAddress,STPhone);


                            for(int i=0;i<Presence_nr;i++)
                            {
                                JSONObject presence = jsonResponse.getJSONObject("Presence"+i);
                                String PDate = presence.getString("PDate");
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                Date date = format.parse(PDate);
                                Boolean PValue = presence.getBoolean("PValue");
                                String SBName = presence.getString("SBName");
                                new Presences(date,PValue,SBName);
                            }
                            for(int i=0;i<Grade_nr;i++)
                            {
                                JSONObject grade = jsonResponse.getJSONObject("Grade"+i);
                                String GDate = grade.getString("GDate");
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                Date date = format.parse(GDate);
                                Integer GValue = grade.getInt("GValue");
                                String SBName = grade.getString("SBName");
                                Boolean GIsTermPapaer = grade.getBoolean("GIsTermPaper");
                                new Grades(date,GValue,SBName, GIsTermPapaer);
                            }

                            for(int i=0;i<Chat_nr;i++)
                            {
                                JSONObject chat = jsonResponse.getJSONObject("Chat"+i);

                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                String CHDate = chat.getString("CHDate");
                                String CHEDate = chat.getString("CHEDate");
                                Date chdate = format.parse(CHDate);
                                Date chedate = format.parse(CHEDate);
                                Integer CHType = chat.getInt("CHType");
                                String CHMessage = chat.getString("CHMessage");
                                String TName = chat.getString("TName");
                                new ChatMessage(chdate,chedate,CHMessage,TName,CHType);
                            }
                            pglogin.dismiss();
                            startActivity(new Intent(StartUp.this, Main.class));
                        }

                    }
                    else{
                        pglogin.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
                        alert.setMessage("Ups.. S-a intamplat ceva neprevazut").setNegativeButton("Inapoi",null).create().show();
                    }
                } catch (JSONException | UnsupportedEncodingException | ParseException e) {
                    pglogin.dismiss();
                    e.printStackTrace();
                }
                pglogin.dismiss();

            }
        };


        _Login_Request login_Request = new _Login_Request(versionCode.toString(),mEmail,mPassword,loginListener);
        RequestQueue login_Queue = Volley.newRequestQueue(StartUp.this);
        login_Queue.add(login_Request);


    }

    private void LinkButtons()
    {
        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button login_button = (Button) findViewById(R.id.login_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitCodeWait();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartUp.this, LoginActivity.class));
            }
        });
    }

    public ProgressDialog pgcode = null;

    private void SubmitCodeWait()
    {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        // Test for connection
        if (netInfo!= null && netInfo.isConnectedOrConnecting()) {

        }
        else {

            AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
            alert.setMessage("Conexiune la internet inexistenta.").setNegativeButton("Inapoi",null).create().show();
            // No conection
            return;
        }

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(StartUp.this);
        progressDialog.setMessage("Va rugam asteptati.");
        progressDialog.setTitle("Verificare cod introdus.");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                pgcode=progressDialog;
                    SubmitCode();
            }
        }).start();
    }

    private void SubmitCode()
    {
        final String input_code = ((EditText)findViewById(R.id.code_editText)).getText().toString();
        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success) {
                        boolean Version = jsonResponse.getBoolean("Version");
                        if (!Version) {
                            pgcode.dismiss();
                            AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
                            alert.setMessage("Versiune veche! Va rugam faceti update aplicatiei!").setNegativeButton("Inapoi", null).create().show();
                            return;
                        }
                        boolean Code = jsonResponse.getBoolean("Code");
                        if (Code) {
                                pgcode.dismiss();
                                String CID = jsonResponse.getString("CID");
                                //String SName = jsonResponse.getString("SName");
                                String CName = jsonResponse.getString("CName");

                                Intent intent = new Intent(StartUp.this, CreateActivity.class);
                                intent.putExtra("Code", input_code);
                                intent.putExtra("CID", CID);
                                //intent.putExtra("SName",SName);
                                intent.putExtra("CName", CName);
                                StartUp.this.startActivity(intent);
                        } else {
                            pgcode.dismiss();
                            AlertDialog.Builder alert = new AlertDialog.Builder(StartUp.this);
                            alert.setMessage("Cod inexistent").setNegativeButton("Inapoi", null).create().show();

                        }
                    }
                } catch (JSONException e) {
                    pgcode.dismiss();
                    e.printStackTrace();
                }

            }
        };

        _Code_Request code_Request = new _Code_Request(versionCode.toString(),input_code,loginListener);
        RequestQueue code_Queue = Volley.newRequestQueue(StartUp.this);
        code_Queue.add(code_Request);
    }
}
