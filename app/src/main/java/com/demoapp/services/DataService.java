package com.demoapp.services;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demoapp.models.AboutItem;
import com.demoapp.utils.Constants;
import com.google.gson.Gson;

import java.util.Arrays;

/**
 * Responsible for fetching the remote data
 */
public class DataService {
    RequestQueue requestQueue;

    public DataService(@Nullable Context context) {
        if (context != null)
            requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Fetches the remove data
     *
     * @param listener
     */
    public void loadData(@Nullable final DataServiceListener listener) {
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AboutItem aboutItem = gson.fromJson(response, AboutItem.class);
                if (aboutItem != null) listener.onSuccess(Arrays.asList(aboutItem.getDataItem()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) listener.onError(error);
            }
        });

        requestQueue.add(request);
    }
}
