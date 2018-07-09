package com.demoapp.presenters;

import com.android.volley.VolleyError;
import com.demoapp.models.DataItem;

import java.util.List;

/**
 * Responsible for all communication between View and Presenter
 */
public interface DataPresenterView {

    /**
     * Call to say view to show the progress screen while loading dataItem list
     *
     * @see DataItem
     */
    void showProgress();

    /**
     * Call to say view to dismiss the progress screen upon items loaded
     *
     * @see DataItem
     */
    void dismissProgress();

    /**
     * Callback on dataItems successfully fetched
     *
     * @see DataItem
     */
    void onSuccess(List<DataItem> list);

    /**
     * Callback on dataItems failed to fetch
     *
     * @see VolleyError
     */
    void onError(VolleyError error);
}
