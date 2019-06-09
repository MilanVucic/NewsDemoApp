package com.vucko.newsdemoapp.api.models;

import com.vucko.newsdemoapp.R;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private int backgroundColorResourceId;

    public Category(String name, int backgroundColor) {
        this.name = name;
        this.backgroundColorResourceId = backgroundColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBackgroundColorResourceId() {
        return backgroundColorResourceId;
    }

    public void setBackgroundColorResourceId(int backgroundColorResourceId) {
        this.backgroundColorResourceId = backgroundColorResourceId;
    }

    // I wanted to fetch categories from the API but couldn't find the endpoint for it
    // So I opted to hardcode them in the app
    public static List<Category> getAllCategories(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("business", R.color.category_1));
        list.add(new Category("entertainment", R.color.category_2));
        list.add(new Category("general", R.color.category_3));
        list.add(new Category("health", R.color.category_4));
        list.add(new Category("science", R.color.category_5));
        list.add(new Category("sports", R.color.category_6));
        list.add(new Category("technology", R.color.category_7));
        return list;
    }
}
