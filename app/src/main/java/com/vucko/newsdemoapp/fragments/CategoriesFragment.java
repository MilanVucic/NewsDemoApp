package com.vucko.newsdemoapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vucko.newsdemoapp.R;
import com.vucko.newsdemoapp.adapters.CategoriesAdapter;
import com.vucko.newsdemoapp.api.models.Category;
import com.vucko.newsdemoapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.categoriesRecyclerView)
    RecyclerView categoriesRecyclerView;

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Constants.CATEGORIES_COLUMNS));
        CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), Category.getAllCategories());
        categoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_categories;
    }
}
