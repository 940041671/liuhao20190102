package com.umeng.soexample.liuhao20190102.di.v;

import okhttp3.Callback;

public interface Contract {
    public interface Main{
        void getData(String url, Callback callback);
    }
    public interface MainPresenter{
        void getData(String url, Callback callback);

        void de();
    }
    public interface MainModel{
        void getData(String url, Callback callback);
    }
}
