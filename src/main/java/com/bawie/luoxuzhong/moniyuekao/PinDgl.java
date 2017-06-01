package com.bawie.luoxuzhong.moniyuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.animation.AnimatorCompatHelper;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.helper.ItemTouchHelper;

        import java.util.ArrayList;
        import java.util.List;

public class PinDgl extends AppCompatActivity
        implements MyOnClickCallback,MyItemThouchHelperCallBack{

    private List<String> list;
    private List<String> list1;
    private MyAdapter myAdapter;
    private MyAdapter myAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pindgl);

        //实例化集合
        list = new ArrayList<>();
        list1 = new ArrayList<>();

        Intent intent = getIntent();
        if (intent!=null){
            ArrayList<String> list3 = intent.getStringArrayListExtra("list1");
            ArrayList<String> list4 = intent.getStringArrayListExtra("list2");
            if (list3!=null && list4!=null){
                list.addAll(list3);
                list1.addAll(list4);
            }
        }


        //发现控件
        RecyclerView topRecyclerV = (RecyclerView) findViewById(R.id.recyclerViewTop);
        RecyclerView bottomRecyclerV = (RecyclerView) findViewById(R.id.recyclerViewBottom);

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        topRecyclerV.setHasFixedSize(true);
        bottomRecyclerV.setHasFixedSize(true);

        //不靠谱-----------------------------------
        topRecyclerV.setItemAnimator(new DefaultItemAnimator());
        bottomRecyclerV.setItemAnimator(new DefaultItemAnimator());

        //gridView布局管理者
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,3);

        //添加布局
        topRecyclerV.setLayoutManager(gridLayoutManager);
        bottomRecyclerV.setLayoutManager(gridLayoutManager1);



        /*//初始化数据
        for (int i = 0; i < 20; i++) {
            list.add("item"+i);
            list1.add("item"+(i+20));
        }*/
        //适配器   参数1：上下文context ，2：list数据集合 ，3：单击监听的接口引用 ，4：适配器的标记
        myAdapter = new MyAdapter(this, list,this,0);
        myAdapter1 = new MyAdapter(this, list1,this,1);

        MyOnItemTouchHelperCallBack myOnItemTouchHelperCallBack = new MyOnItemTouchHelperCallBack(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myOnItemTouchHelperCallBack);
        MyItemMoveListener myItemMoveListener = new MyItemMoveListener(this);
        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(myItemMoveListener);

        //用Callback构造ItemtouchHelper
        itemTouchHelper.attachToRecyclerView(topRecyclerV);
        itemTouchHelper1.attachToRecyclerView(bottomRecyclerV);

        //设置适配器
        topRecyclerV.setAdapter(myAdapter);
        bottomRecyclerV.setAdapter(myAdapter1);


    }

    /**
     * 单击的回调方法
     * @param index  集合的下标索引，相当于position
     * @param adapterIndex  adapter的标记
     */
    @Override
    public void clickCallBack(int index,int adapterIndex) {
        if (adapterIndex==0){
            list1.add(list.get(index));
            list.remove(index);

            myAdapter.notifyDataSetChanged();
            myAdapter1.notifyDataSetChanged();
        }else if (adapterIndex == 1){
            list.add(list1.get(index));
            list1.remove(index);

            myAdapter.notifyDataSetChanged();
            myAdapter1.notifyDataSetChanged();
        }
    }


    @Override
    /**
     * item选中
     */
    public void onItemSelected(RecyclerView.ViewHolder viewHolder,int adapterIndex ) {
        if (adapterIndex==0){
            //当拖拽选中时放大选中的view
            int position = viewHolder.getAdapterPosition();
            viewHolder.itemView.setScaleX(1.2f);
            viewHolder.itemView.setScaleY(1.2f);
        }
    }

    @Override
    /**
     * item 移动交换
     */
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target,int adapterIndex ) {

        if (adapterIndex == 0){
            int position = source.getAdapterPosition();
            int tooPosition = target.getAdapterPosition();

            String shuju =  list.get(position);
            list.remove(position);
            list.add(tooPosition,shuju);

            myAdapter.notifyItemMoved(position,tooPosition);

            source.itemView.setScaleX(1.0f);
            source.itemView.setScaleY(1.0f);
        }if (adapterIndex == 1){
            int position = source.getAdapterPosition();
            int tooPosition = target.getAdapterPosition();

            String shuju =  list.get(position);
            list1.remove(position);
            list1.add(tooPosition,shuju);

            myAdapter1.notifyItemMoved(position,tooPosition);

            source.itemView.setScaleX(1.0f);
            source.itemView.setScaleY(1.0f);
        }


    }

}
