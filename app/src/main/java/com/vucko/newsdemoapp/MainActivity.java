package com.vucko.newsdemoapp;

import android.widget.Toast;

import com.vucko.newsdemoapp.api.ApiHelper;
import com.vucko.newsdemoapp.api.OnDataCallback;
import com.vucko.newsdemoapp.api.models.NewsResponseModel;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI() {
        ApiHelper.getInstance().getAllHeadlines("us", new OnDataCallback<NewsResponseModel>() {
            @Override
            public void onSuccess(NewsResponseModel data) {
                Toast.makeText(MainActivity.this, "Nadjoh ovoliko vjesti: " + data.getTotalResults(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(MainActivity.this, "DAFUQ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
