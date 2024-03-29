package com.vucko.newsdemoapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        setupUI();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected void setToolbarBackArrow(boolean enabled){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    protected void setToolbarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    protected void setupUI() {

    }
}
