package com.bawie.luoxuzhong.moniyuekao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

/**
 * Created by 罗许忠
 * on 2017/6/1 15:39
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Bean.ResultBean.IndexProductsBean> list;

    public MyGridViewAdapter(Context context, List<Bean.ResultBean.IndexProductsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null ;
        if (convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = View.inflate(context,R.layout.griditem,null);

            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.itemImage);
            myViewHolder.textView = (TextView) convertView.findViewById(R.id.itemText);

            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        x.image().bind(myViewHolder.imageView,list.get(position).getPic());
        myViewHolder.textView.setText(list.get(position).getName());

        return convertView;
    }

    static class  MyViewHolder{
        ImageView imageView ;
        TextView textView;
    }
}
