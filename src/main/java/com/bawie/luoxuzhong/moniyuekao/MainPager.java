package com.bawie.luoxuzhong.moniyuekao;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗许忠
 * on 2017/6/1 10:17
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MainPager extends Fragment {

    //轮播
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                if (vp!= null){
                    if (listVP != null){
                        vp.setCurrentItem(current);
                        current++;
                        if (current == listVP.size()){
                            current = 0;
                        }
                        handler.sendEmptyMessageDelayed(0,3000);
                    }
                }
            }
        }
    };
    int current = 0;
    private ViewPager vp;
    private List<String> listVP;
    private Bean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mainpageer,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isNetworkConnected(getActivity())){
            //有网
            zhuLJ();
            TextView gengduo = (TextView) getView().findViewById(R.id.gengduo);
            gengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v     ) {
                    Intent intent = new Intent(getActivity(),PinDgl.class);
                    if (bean!=null){
                        ArrayList<String> list1 = new ArrayList<String>();
                        ArrayList<String> list2 = new ArrayList<String>();
                        for (int i = 0; i < bean.getResult().getCategory().size(); i++) {
                            list1.add(bean.getResult().getCategory().get(i).getName());
                        }
                        for (int i = 0; i < bean.getResult().getNations().size(); i++) {
                            list2.add(bean.getResult().getNations().get(i).getName());
                        }

                        intent.putStringArrayListExtra("list1",list1);
                        intent.putStringArrayListExtra("list2",list2);

                        startActivity(intent);
                    }
                }
            });
        }else {
            //没网
            Toast.makeText(getActivity(),"当前无网络，正在跳转设置界面",Toast.LENGTH_SHORT).show();
            openSetting(getActivity());
        }

    }

    private void zhuLJ() {
        x.http().get(new RequestParams("http://www.babybuy100.com/API/getShopOverview.ashx"), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    Log.e("onSuccess: ","请求成功！！！" );
                    Gson gson = new Gson();
                    bean = gson.fromJson(result,Bean.class);
                    listVP = new ArrayList<String>();
                    for (int i = 0; i < bean.getResult().getTopics().size(); i++) {
                        listVP.add(bean.getResult().getTopics().get(i).getPic());
                    }
                    //轮播
                    lunbo(listVP);

                    //gridView
                    grid(bean.getResult().getIndexProducts());
                }
            }



            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void grid(List<Bean.ResultBean.IndexProductsBean> listGrid) {
        GridView gridView = (GridView) getView().findViewById(R.id.mainGridView);
        MyGridViewAdapter myGridAdapter = new MyGridViewAdapter(getActivity(),listGrid);
        gridView.setAdapter(myGridAdapter);
    }

    /**
     * view pager轮播
     * @param listVP
     */
    private void lunbo(List<String> listVP) {
        vp = (ViewPager) getView().findViewById(R.id.viewPager);
        MyPagetAdapter myAdapter = new MyPagetAdapter(getActivity(),listVP);
        vp.setAdapter(myAdapter);
        handler.sendEmptyMessage(0);
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 判断是否有网络链接
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
             if (context != null) {
                     ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                             .getSystemService(Context.CONNECTIVITY_SERVICE);
                     NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                     if (mNetworkInfo != null) {
                             return mNetworkInfo.isAvailable();
                         }
                 }
             return false;
         }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
