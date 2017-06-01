package com.bawie.luoxuzhong.moniyuekao;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPagerChecked();
    }

    private void mainPagerChecked() {
        final FragmentControls fragmentControls = new FragmentControls(getSupportFragmentManager(),R.id.frame);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        fragmentControls.showFragment(0);
                        break;
                    case R.id.rb2:
                        fragmentControls.showFragment(1);
                        break;
                    case R.id.rb3:
                        fragmentControls.showFragment(2);
                        break;
                    case R.id.rb4:
                        fragmentControls.showFragment(3);
                        break;
                }
            }
        });
    }
}
