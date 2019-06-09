package com.vucko.newsdemoapp.adapters;

import com.vucko.newsdemoapp.fragments.CategoriesFragment;
import com.vucko.newsdemoapp.fragments.FavouritesFragment;
import com.vucko.newsdemoapp.fragments.SearchFragment;
import com.vucko.newsdemoapp.fragments.TopNewsFragment;
import com.vucko.newsdemoapp.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = TopNewsFragment.newInstance();
                break;
            case 1:
                fragment = CategoriesFragment.newInstance();
                break;
            case 2:
                fragment = FavouritesFragment.newInstance();
                break;
            case 3:
                fragment = SearchFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.titles[position];
    }

    @Override
    public int getCount() {
        return Constants.titles.length;
    }
}
