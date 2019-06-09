package com.vucko.newsdemoapp.fragments;

import android.os.Bundle;

import com.vucko.newsdemoapp.R;

public class SearchFragment extends BaseFragment {

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
    protected int getLayoutResourceId() {
        return R.layout.fragment_search;
    }

}
