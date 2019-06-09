package com.vucko.newsdemoapp;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.vucko.newsdemoapp.adapters.NewsAdapter;
import com.vucko.newsdemoapp.api.ApiHelper;
import com.vucko.newsdemoapp.api.OnDataCallback;
import com.vucko.newsdemoapp.api.models.Category;
import com.vucko.newsdemoapp.api.models.NewsArticle;
import com.vucko.newsdemoapp.api.models.NewsResponseModel;
import com.vucko.newsdemoapp.utils.Constants;
import com.vucko.newsdemoapp.utils.GeneralUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    public static void start(Context context, Category category) {
        Intent starter = new Intent(context, CategoryActivity.class);
        starter.putExtra(Constants.CATEGORY, category.getName());
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_category;
    }

    @Override
    protected void setupUI() {
        if(getIntent() != null){
            String categoryName = getIntent().getStringExtra(Constants.CATEGORY);
            setToolbarBackArrow(true);
            setToolbarTitle(categoryName);
            if(!TextUtils.isEmpty(categoryName)){
                getNewsForCategory(categoryName);
            }
        }
    }

    private void getNewsForCategory(String categoryName) {
        ApiHelper.getInstance().getAllHeadlines(Constants.COUNTRY, categoryName, new OnDataCallback<NewsResponseModel>() {
            @Override
            public void onSuccess(NewsResponseModel data) {
                updateUI(data.getArticles());
            }

            @Override
            public void onFailure(String message) {
                GeneralUtils.displayError(message, CategoryActivity.this);
            }
        });
    }

    private void showLoader() {
        loader.smoothToShow();
    }

    private void hideLoader() {
        loader.smoothToHide();
    }

    private void updateUI(List<NewsArticle> articles) {
        hideLoader();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter adapter = new NewsAdapter(this, articles);
        newsRecyclerView.setAdapter(adapter);
    }
}
