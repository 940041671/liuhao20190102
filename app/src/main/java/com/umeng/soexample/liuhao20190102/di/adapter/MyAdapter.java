package com.umeng.soexample.liuhao20190102.di.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.umeng.soexample.liuhao20190102.R;
import com.umeng.soexample.liuhao20190102.di.bean.Shou;
import com.umeng.soexample.liuhao20190102.di.bean.Xiang;
import com.umeng.soexample.liuhao20190102.di.p.MainPresenter;
import com.umeng.soexample.liuhao20190102.di.v.Contract;
import com.umeng.soexample.liuhao20190102.ui.Main2Activity;
import com.umeng.soexample.liuhao20190102.ui.MainActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Shou.DataBean.TuijianBean.ListBeanX> list;
    private Contract.MainPresenter mainPresenter;

    public MyAdapter(MainActivity context, Contract.MainPresenter mainPresenter) {
        this.context = context;
        this.mainPresenter=mainPresenter;
    }

    public void setList( List<Shou.DataBean.TuijianBean.ListBeanX> list){
        this.list=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImages()).into(holder.img);
        holder.text.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView text;
        private final TextView price;

        public MyViewHolder(final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.de);
            price = itemView.findViewById(R.id.price);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    list.remove(getLayoutPosition());
                    setList(list);
                    notifyDataSetChanged();
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String utl="http://www.zhaoapi.cn/product/getProductDetail?pid="+list.get(getLayoutPosition()).getPid();
                    mainPresenter.getData(utl, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            Intent intent = new Intent(context, Main2Activity.class);
                            intent.putExtra("data",response.body().string());
                            context.startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}
