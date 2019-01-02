package com.umeng.soexample.liuhao20190102.di;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp网络工具类
 */
public class NetWorkUtils {
    //单例模式
    private NetWorkUtils() {
    }
    static NetWorkUtils netWorkUtils=new NetWorkUtils();
    public static NetWorkUtils getInstand(){
        return netWorkUtils;
    }

    //get
    public void get(String url, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        }).build();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
