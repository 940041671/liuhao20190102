package com.umeng.soexample.liuhao20190102.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.umeng.soexample.liuhao20190102.R;
import com.umeng.soexample.liuhao20190102.di.bean.Xiang;

public class Main2Activity extends AppCompatActivity {

    private ImageView image;
    private TextView de2;
    private TextView price2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiang);
        initView();
        String data = getIntent().getStringExtra("data");
        Gson gson = new Gson();
        Xiang xiang = gson.fromJson(data, Xiang.class);
        Glide.with(this).load(xiang.getSeller().getIcon()).into(image);
        de2.setText(xiang.getData().getTitle());
        price2.setText("￥"+xiang.getData().getBargainPrice());
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        de2 = (TextView) findViewById(R.id.de2);
        price2 = (TextView) findViewById(R.id.price2);
    }
}
