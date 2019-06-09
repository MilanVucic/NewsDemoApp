package com.vucko.newsdemoapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;
    @BindView(R.id.keywordEditText)
    EditText keywordEditText;
    @BindView(R.id.nothingFoundTextView)
    TextView nothingFoundTextView;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideLoader();
    }

    @OnClick(R.id.submitButton)
    public void submitClicked(View view) {
        if (TextUtils.isEmpty(keywordEditText.getText())) {
            keywordEditText.setError(getString(R.string.required_field_empty));
            return;
        }
        getNewsForKeyword(keywordEditText.getText().toString());
    }

    private void getNewsForKeyword(String keyword) {
        showLoader();
        nothingFoundTextView.setVisibility(View.GONE);
        ApiHelper.getInstance().getAllHeadlines(Constants.COUNTRY, null, keyword, new OnDataCallback<NewsResponseModel>() {
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
        if (articles.size() == 0) {
            nothingFoundTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_search;
    }

}
