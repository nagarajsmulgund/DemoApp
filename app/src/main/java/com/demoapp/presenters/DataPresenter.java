package com.demoapp.presenters;

import android.content.Context;

import com.android.volley.VolleyError;
import com.demoapp.models.DataItem;
import com.demoapp.services.DataService;
import com.demoapp.services.DataServiceListener;

import java.util.List;

/**
 * Responsible for all operations
 * Gets the command from UI and guides the UI what to do and guider the interator {@link DataService} about what to do.
 */
public class DataPresenter implements DataServiceListener {
    private DataPresenterView view;
    private DataService dataService;
    private List<DataItem> list;

    public DataPresenter(DataPresenterView view, Context context) {
        this.view = view;
        dataService = new DataService(context);
    }

    public void loadData() {
        if (view != null) {
            view.showProgress();
            dataService.loadData(this);
        }
    }

    @Override
    public void onError(VolleyError error) {
        if (view != null) {
            view.dismissProgress();
            view.onError(error);
        }
    }

    @Override
    public void onSuccess(List<DataItem> list) {
        this.list = list;
        if (view != null) {
            view.dismissProgress();
            if (list == null) {
                view.onError(new VolleyError());// set required exception
            } else {
                view.onSuccess(list);
            }
        }
    }

    public List<DataItem> getList() {
        return list;
    }
}
