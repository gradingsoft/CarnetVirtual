package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    String Class;
    String CID;
    String Code;

    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

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
                    ProgressBar();
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
                try {
                    CheckSubmit();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
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
                Toast.makeText(this,"1 "+ BitmapCompat.getAllocationByteCount(bitmap), Toast.LENGTH_SHORT).show();
                //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
                //InputStream in = new ByteArrayInputStream(bos.toByteArray());
                //bitmap = BitmapFactory.decodeStream(in);
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 414, 550, true);
                Toast.makeText(this,"2 "+ BitmapCompat.getAllocationByteCount(bitmap1), Toast.LENGTH_SHORT).show();

                imagetest.setImageBitmap(bitmap1);
                STimage=bitmap1;
            }
        }else
        {
            Toast.makeText(this,"Eroare poza",Toast.LENGTH_LONG).show();

        }

    }


    private void CheckSubmit() throws UnsupportedEncodingException {
        EditText tname = (EditText) findViewById(R.id.name_editText);
        EditText tforename = (EditText) findViewById(R.id.forname_editText);
        EditText temail = (EditText) findViewById(R.id.email_editText);
        EditText tcnp = (EditText) findViewById(R.id.cnp_editText);
        EditText tphone_number = (EditText) findViewById(R.id.phone_editText);
        EditText tpass1 = (EditText) findViewById(R.id.pass1_editText);
        EditText tpass2 = (EditText) findViewById(R.id.pass2_editText);
        EditText taddress = (EditText) findViewById(R.id.adress_editText);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        STimage.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String STImage=Base64.encodeToString(b, Base64.DEFAULT);

        String name        =tname.getText().toString();
        String forename    =tforename.getText().toString();
        String email       =temail.getText().toString();
        String cnp         =tcnp.getText().toString();
        String phone_number=tphone_number.getText().toString();
        String pass1       =tpass1.getText().toString();
        String pass2       =tpass2.getText().toString();
        String address     =taddress.getText().toString();

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
        _Register_Request register_Request = new _Register_Request(Code,name,forename,email,cnp,phone_number,CID,pass1,address,STImage,loginListener);
        RequestQueue register_Queue = Volley.newRequestQueue(CreateActivity.this);
        register_Queue.add(register_Request);

    }


}
