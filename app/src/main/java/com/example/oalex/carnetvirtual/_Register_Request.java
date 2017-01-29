package com.example.oalex.carnetvirtual;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad_ on 29.01.2017.
 */

public class _Register_Request extends StringRequest {
    private static final String Site_URL_Login = "http://pasotee.5gbfree.com/register_request.php";
    private Map<String, String> params;

    public _Register_Request(String Name,String FirstName,String Email,String CNP,String Phone,String Class, String Password,String Address, Response.Listener<String> listener) {
        super(Method.POST, Site_URL_Login, listener, null);
        params = new HashMap<>();
        params.put("Name", Name);
        params.put("FirstName", FirstName);
        params.put("Email", Email);
        params.put("CNP",CNP);
        params.put("Phone", Phone);
        params.put("Class", Class);
        params.put("Password",Password);
        params.put("Address",Address);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}