package com.example.oalex.carnetvirtual;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad_ on 28.01.2017.
 */

public class Code_Request extends StringRequest {
    private static final String Site_URL_Login = "http://pasotee.5gbfree.com/code_request.php";
    private Map<String, String> params;

    public Code_Request(String Code, Response.Listener<String> listener) {
        super(Method.POST, Site_URL_Login, listener, null);
        params = new HashMap<>();
        params.put("Code", Code);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}