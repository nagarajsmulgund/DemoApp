package com.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.demoapp.models.DataItem;
import com.demoapp.presenters.DataPresenter;
import com.demoapp.presenters.DataPresenterView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataPresenterView {

    private DataPresenter dataPresenter;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private FrameLayout progressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView = findViewById(R.id.progress_view);

        configureRecyclerView();

        dataPresenter = new DataPresenter(this, this);
        dataPresenter.loadData();
    }

    private void configureRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<DataItem> list) {
        if (dataAdapter == null) {
            dataAdapter = new DataAdapter(list, this);
            recyclerView.setAdapter(dataAdapter);
        } else {
            dataAdapter.updateData(list);
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(VolleyError error) {
        // Show an appropriate error dialog
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
