package com.vucko.newsdemoapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.vucko.newsdemoapp.R;
import com.vucko.newsdemoapp.adapters.NewsAdapter;
import com.vucko.newsdemoapp.api.models.NewsArticle;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends BaseFragment {

    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;
    @BindView(R.id.nothingFoundTextView)
    TextView nothingFoundTextView;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    public static FavouritesFragment newInstance() {
        FavouritesFragment fragment = new FavouritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Query builder didn't work properly, had to resort to this
        List<NewsArticle> articles = NewsArticle.findWithQuery(NewsArticle.class, "Select * from news_article where favourite = ?", "1");
        updateUI(articles);
    }

    private void updateUI(List<NewsArticle> articles) {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter adapter = new NewsAdapter(getActivity(), articles);
        newsRecyclerView.setAdapter(adapter);
        if (articles.size() == 0) {
            nothingFoundTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_favourites;
    }
}
