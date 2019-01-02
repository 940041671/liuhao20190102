package com.umeng.soexample.liuhao20190102.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.liuhao20190102.R;
import com.umeng.soexample.liuhao20190102.di.adapter.MyAdapter;
import com.umeng.soexample.liuhao20190102.di.bean.Shou;
import com.umeng.soexample.liuhao20190102.di.p.MainPresenter;
import com.umeng.soexample.liuhao20190102.di.v.Contract;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Contract.Main {

    private RecyclerView recy;
    private Contract.MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter = new MainPresenter();
        getData();
    }

    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);
    }

    public void getData(){
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);
        ((MainPresenter) mainPresenter).getContext(MainActivity.this);
        //网络请求接口获取数据
        mainPresenter.getData("http://www.zhaoapi.cn/home/getHome", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Shou shou = gson.fromJson(string, Shou.class);
                        List<Shou.DataBean.TuijianBean.ListBeanX> list = shou.getData().getTuijian().getList();
                        MyAdapter adapter=new MyAdapter(MainActivity.this,mainPresenter);
                        adapter.setList(list);
                        recy.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.de();
    }

    @Override
    public void getData(String url, Callback callback) {

    }
}
