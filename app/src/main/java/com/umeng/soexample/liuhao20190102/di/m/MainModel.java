package com.umeng.soexample.liuhao20190102.di.m;

import com.umeng.soexample.liuhao20190102.di.NetWorkUtils;
import com.umeng.soexample.liuhao20190102.di.v.Contract;

import okhttp3.Callback;


public class MainModel implements Contract.MainModel {

    @Override
    public void getData(String url, Callback callback) {
        NetWorkUtils.getInstand().get(url,callback);
    }
}
