package com.demoapp.services;

import com.android.volley.VolleyError;
import com.demoapp.models.DataItem;

import java.util.List;

/**
 * Helps in establishing the connection between DataService and DataPresenter by posting the
 * results and error
 */
public interface DataServiceListener {
    /**
     * Called during the error case
     */
    void onError(VolleyError error);

    /**
     * Called during the error case
     */
    void onSuccess(List<DataItem> list);
}