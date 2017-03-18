package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad_ on 28.01.2017.
 */

class _Login_Request extends StringRequest {
    private static final String Site_URL_Login = "http://carnet-virtual.victoriacentre.ro/login_request.php";
    private Map<String, String> params;

    _Login_Request(String Email, String Password, Response.Listener<String> listener) {
        super(Method.POST, Site_URL_Login, listener, null);
        String AccessCode = "565656";
        params = new HashMap<>();
        params.put("AccessCode",AccessCode);
        params.put("Email", Email);
        params.put("Password",Password);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}