package com.bawie.luoxuzhong.moniyuekao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.x;

import java.util.List;

/**
 * Created by 罗许忠
 * on 2017/6/1 14:46
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyPagetAdapter extends PagerAdapter {
    private List<String> listVP;
    private Context context;

    public MyPagetAdapter(Context context, List<String> listVP){
        this.context = context;
        this.listVP = listVP;
    }

    @Override
    public int getCount() {
        return listVP.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        x.image().bind(imageView,listVP.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
