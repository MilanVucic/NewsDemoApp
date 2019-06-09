package com.vucko.newsdemoapp.fragments;


import android.os.Bundle;
import android.view.View;

import com.vucko.newsdemoapp.R;
import com.vucko.newsdemoapp.adapters.NewsAdapter;
import com.vucko.newsdemoapp.api.ApiHelper;
import com.vucko.newsdemoapp.api.OnDataCallback;
import com.vucko.newsdemoapp.api.models.NewsArticle;
import com.vucko.newsdemoapp.api.models.NewsResponseModel;
import com.vucko.newsdemoapp.utils.Constants;
import com.vucko.newsdemoapp.utils.GeneralUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopNewsFragment extends BaseFragment {
    
    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;
    
    public TopNewsFragment() {
        // Required empty public constructor
    }

    public static TopNewsFragment newInstance() {
        TopNewsFragment fragment = new TopNewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTopNews();
    }

    private void getTopNews() {
        showLoader();
        ApiHelper.getInstance().getAllHeadlines(Constants.COUNTRY, null, new OnDataCallback<NewsResponseModel>() {
            @Override
            public void onSuccess(NewsResponseModel data) {
                updateUI(data.getArticles());
            }

            @Override
            public void onFailure(String message) {
                GeneralUtils.displayError(message, getActivity());
                hideLoader();
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
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter adapter = new NewsAdapter(getActivity(), articles);
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_top_news;
    }
}
