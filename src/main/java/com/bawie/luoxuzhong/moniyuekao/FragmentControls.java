package com.bawie.luoxuzhong.moniyuekao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class FragmentControls {
    private FragmentManager fragmentManager;
    private int frameLayoutId;
    private List<Fragment> list;

    public FragmentControls(FragmentManager fragmentManager,int frameLayoutId){
        this.fragmentManager = fragmentManager;
        this.frameLayoutId = frameLayoutId;
        list = new ArrayList<>();
        one();
    }

    private void one() {
        FragmentTransaction ft = fragmentManager.beginTransaction();


        list.add(new MainPager());
        list.add(new Video());
        list.add(new VsheQu());
        list.add(new Me());


        ft.add(frameLayoutId,list.get(0));
        ft.add(frameLayoutId,list.get(1));
        ft.add(frameLayoutId,list.get(2));
        ft.add(frameLayoutId,list.get(3));

        ft.commit();

        //默认显示第一个
        showFragment(0);
    }

    public void showFragment(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        for (int i = 0; i < list.size(); i++) {
            ft.hide(list.get(i));
        }

        ft.show(list.get(index));
        ft.commit();
    }


}
