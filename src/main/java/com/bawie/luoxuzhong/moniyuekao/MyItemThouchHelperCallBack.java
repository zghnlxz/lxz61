package com.bawie.luoxuzhong.moniyuekao;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 罗许忠
 * on 2017/5/27 15:24
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public interface MyItemThouchHelperCallBack {
    //数据选中
    void onItemSelected(RecyclerView.ViewHolder viewHolder, int adapterIndex);
    //数据交换
    void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target, int adapterIndex);
}
