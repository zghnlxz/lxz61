package com.bawie.luoxuzhong.moniyuekao;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 罗许忠
 * on 2017/6/1 14:31
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
