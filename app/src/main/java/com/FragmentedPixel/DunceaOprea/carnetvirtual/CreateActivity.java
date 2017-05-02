package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    String Class;
    String CID;
    String Code;

    final int PIC_CROP = 2;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap STimage;
    boolean writeAccepted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        Intent intent = getIntent();

        EditText clasa_editText = (EditText) findViewById(R.id.clasa_editText);
        Class = intent.getStringExtra("CName");
        CID = intent.getStringExtra("CID");
        Code = intent.getStringExtra("Code");
        clasa_editText.setText(Class);
        clasa_editText.setInputType(0);

        final Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CheckSubmit();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //submitButton.setEnabled(false);

            }
        });

        Button selectImage = (Button) findViewById(R.id.imagine_button);
        selectImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SelectPoza();
            }
        });
    }

    public ProgressDialog pg = null;

    private void ProgressBar()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        // Test for connection
        if (netInfo!= null && netInfo.isConnectedOrConnecting()) {}
        else {

            AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
            alert.setMessage("Conexiune la internet inexistenta.").setNegativeButton("Inapoi",null).create().show();
            // No conection
            return;
        }

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CreateActivity.this);
        progressDialog.setMessage("Va rugam asteptati.");
        progressDialog.setTitle("Incarcare date");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                SendSubmit();
                pg=progressDialog;
            }
        }).start();
    }

    private void SelectPoza()
    {
        String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        int permsRequestCode = 200;
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(perms, permsRequestCode);
            startActivityForResult(i, RESULT_LOAD_IMAGE);

        }
        else
            writeAccepted=true;
        if(writeAccepted)
        {

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        switch(permsRequestCode) {

            case 200:

                writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();

            CropImage.activity(selectedImage)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(3,4)
                    .start(this);

        }
        else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                ImageView imagetest = (ImageView) findViewById(R.id.testimg);
                imagetest.setImageURI(resultUri);
                Bitmap bitmap = ((BitmapDrawable)imagetest.getDrawable()).getBitmap();
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 414, 552, true);

                imagetest.setImageBitmap(bitmap1);
                STimage=bitmap1;
            }
        }else
        {
            Toast.makeText(this,"Eroare poza",Toast.LENGTH_LONG).show();

        }

    }

    private void SendSubmit() {

        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    boolean code_access = jsonResponse.getBoolean("code_access");
                    boolean success = jsonResponse.getBoolean("success");
                    boolean email_free= jsonResponse.getBoolean("email_free");
                    if(code_access)
                    {
                        if(email_free)
                        {
                            if (success)
                            {
                                pg.dismiss();
                                AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                                alert.setMessage("Cont creat cu succes").setNegativeButton("Inapoi", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent I = new Intent(CreateActivity.this, StartUp.class);
                                        startActivity(I);
                                    }
                                }).create().show();

                            }
                            else
                            {
                                pg.dismiss();
                                AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                                alert.setMessage("Eroare la creare cont").setNegativeButton("", null).create().show();

                            }
                        }
                        else
                        {
                            pg.dismiss();
                            AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                            alert.setMessage("Acest email exista deja.").setNegativeButton("Inapoi", null).create().show();

                        }
                    }
                    else
                    {
                        pg.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                        alert.setMessage("Cod gresit").setNegativeButton("Inapoi",null).create().show();
                        Intent I = new Intent(CreateActivity.this, StartUp.class);
                        startActivity(I);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        _Register_Request register_Request = new _Register_Request(Code,name,forename,email,cnp,phone_number,CID,pass1,address,STImageb64,loginListener);
        RequestQueue register_Queue = Volley.newRequestQueue(CreateActivity.this);
        register_Queue.add(register_Request);


    }

    String name;
    String forename;
    String email;
    String cnp;
    String phone_number;
    String pass1;
    String address;
    String STImageb64;

    private void CheckSubmit() throws UnsupportedEncodingException {
        EditText tname = (EditText) findViewById(R.id.name_editText);
        EditText tforename = (EditText) findViewById(R.id.forname_editText);
        EditText temail = (EditText) findViewById(R.id.email_editText);
        EditText tcnp = (EditText) findViewById(R.id.cnp_editText);
        EditText tphone_number = (EditText) findViewById(R.id.phone_editText);
        EditText tpass1 = (EditText) findViewById(R.id.pass1_editText);
        EditText tpass2 = (EditText) findViewById(R.id.pass2_editText);
        EditText taddress = (EditText) findViewById(R.id.adress_editText);

        if(STimage!=null)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            STimage.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            STImageb64 = Base64.encodeToString(b, Base64.DEFAULT);

        }
        else
        {
            Toast.makeText(this, "Trebuie sa selectati o poza.", Toast.LENGTH_SHORT).show();
            return;
        }

         name        =tname.getText().toString();
         forename    =tforename.getText().toString();
         email       =temail.getText().toString();
         cnp         =tcnp.getText().toString();
         phone_number=tphone_number.getText().toString();
         pass1       =tpass1.getText().toString();
         String pass2       =tpass2.getText().toString();
         address     =taddress.getText().toString();

        ArrayList<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(forename);
        fields.add(email);
        fields.add(cnp);
        fields.add(phone_number);
        fields.add(pass1);
        fields.add(pass2);
        fields.add(address);

        for (String field: fields)
            if (field.equals("")) {
                Toast.makeText(this, "Toate campurile trebuie completate.", Toast.LENGTH_SHORT).show();
                return;
            }


        if(!email.contains("@"))
        {
            Toast.makeText(this, "E-mailul nu contine simbolul '@'.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(! pass1.equals(pass2))
        {
            Toast.makeText(this, "Parolele nu sunt identice.", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressBar();
    }


}
