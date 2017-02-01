package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad_ on 29.01.2017.
 */

public class _Register_Request extends StringRequest {
    private static final String Site_URL_Login = "http://carnet-virtual.victoriacentre.ro/register_request.php";
    private Map<String, String> params;

    public _Register_Request(String Code,String Name, String FirstName, String Email, String CNP, String Phone, String Class, String Password, String Address, String Picture, Response.Listener<String> listener) {
        super(Method.POST, Site_URL_Login, listener, null);
        params = new HashMap<>();
        params.put("AccessCode","123456789");
        params.put("Code",Code);
        params.put("Name", Name);
        params.put("FirstName", FirstName);
        params.put("Email", Email);
        params.put("CNP",CNP);
        params.put("Phone", Phone);
        params.put("Class", Class);
        params.put("Password",Password);
        params.put("Address",Address);
        params.put("Picture",Picture);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}