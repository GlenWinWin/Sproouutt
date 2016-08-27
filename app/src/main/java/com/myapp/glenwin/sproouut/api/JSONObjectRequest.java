package com.myapp.glenwin.sproouut.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class JSONObjectRequest extends JsonObjectRequest {

    private static final String TAG = JSONObjectRequest.class.getSimpleName();

    private Context context;

    public JSONObjectRequest(int method, String url, JSONObject jsonRequest,
                             Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener, Context context) {
        super(method, url, jsonRequest, listener, errorListener);
        this.context = context;
    }

    public JSONObjectRequest(String url, JSONObject jsonRequest,
                             Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

}
