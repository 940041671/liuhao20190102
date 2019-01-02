package com.umeng.soexample.liuhao20190102.di.p;

import com.umeng.soexample.liuhao20190102.di.m.MainModel;
import com.umeng.soexample.liuhao20190102.di.v.Contract;

import okhttp3.Callback;

public class MainPresenter implements Contract.MainPresenter {
    private Contract.Main Imainpresenter;
    Contract.MainModel model=new MainModel();
    public void getContext(Contract.Main Imainpresenter){
        this.Imainpresenter=Imainpresenter;
    }
    @Override
    public void getData(String url, Callback callback) {
        model.getData(url,callback);
    }
    @Override
    public void de(){
        Imainpresenter=null;
    }
}
