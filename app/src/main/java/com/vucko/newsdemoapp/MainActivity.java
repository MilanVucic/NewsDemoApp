package com.vucko.newsdemoapp;

import android.widget.Toast;

import com.vucko.newsdemoapp.adapters.MainPagerAdapter;
import com.vucko.newsdemoapp.api.ApiHelper;
import com.vucko.newsdemoapp.api.OnDataCallback;
import com.vucko.newsdemoapp.api.models.NewsResponseModel;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI() {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);
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
