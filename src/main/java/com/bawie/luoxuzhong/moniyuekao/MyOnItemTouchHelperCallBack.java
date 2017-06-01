package com.bawie.luoxuzhong.moniyuekao;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 罗许忠
 * on 2017/5/27 15:10
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyOnItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private MyItemThouchHelperCallBack myItemThouchHelperCallBack;

    public MyOnItemTouchHelperCallBack(MyItemThouchHelperCallBack myItemThouchHelperCallBack){
        this.myItemThouchHelperCallBack = myItemThouchHelperCallBack;
    }

    @Override
    /**
     * 获得可以活动的标记  《允许上下左右的拖动！》
     */
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int falgs = ItemTouchHelper.DOWN|ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeMovementFlags(falgs,0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //通过接口传递拖拽交换数据的起始位置和目标位置的ViewHolder
        myItemThouchHelperCallBack.onItemMove(viewHolder,target,0);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //移动删除回调，不用可以不回调
    }

    @Override
    /**
     *  启动长按拖拽
     */
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    /**
     * 不启动拖拽删除
     */
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            ////当滑动或者拖拽view的时候通过接口返回该ViewHolder
            myItemThouchHelperCallBack.onItemSelected(viewHolder,0);
        }
    }



    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!recyclerView.isComputingLayout()) {
            //当需要清除之前在onSelectedChanged或者onChildDraw,onChildDrawOver设置的状态或者动画时通过接口返回该ViewHolder
//            mAdapter.onItemClear(viewHolder);
        }
    }
}
