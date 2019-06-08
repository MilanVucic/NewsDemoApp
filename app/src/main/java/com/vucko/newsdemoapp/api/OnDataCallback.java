package com.vucko.newsdemoapp.api;

public interface OnDataCallback<T> {
    void onSuccess(T data);
    void onFailure(String message);
}