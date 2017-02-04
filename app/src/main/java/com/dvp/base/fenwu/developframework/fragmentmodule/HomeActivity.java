package com.dvp.base.fenwu.developframework.fragmentmodule;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.ui.activity.BlankFragment1;
import com.dvp.base.fenwu.developframework.ui.activity.BlankFragment4;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
{

    @Bind(R.id.back_btn)
    Button backBtn;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.content_fl)
    FrameLayout contentFl;
    @Bind(R.id.activity_home)
    LinearLayout activityHome;


    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    @Bind(R.id.search_tv)
    TextView searchTv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        BlankFragment1 fragment1 = new BlankFragment1();
       /* BlankFragment2 fragment2 = new BlankFragment2();
        BlankFragment3 fragment3 = new BlankFragment3();
        BlankFragment4 fragment4 = new BlankFragment4();*/


        fragmentManager = getFragmentManager();
        transaction = fragmentManager
                .beginTransaction();
        transaction.replace(R.id.content_fl, fragment1);
       /* transaction.add(R.id.content_fl, fragment2);
        transaction.add(R.id.content_fl, fragment3);
        transaction.add(R.id.content_fl, fragment4);*/
        transaction.commit();
    }

    @OnClick(R.id.back_btn)
    public void onClick()
    {
        // 返回上一个fragment
        fragmentManager.popBackStack();
    }

    @OnClick(R.id.search_tv)
    public void onClick1()
    {
        BlankFragment4 blankFragment4 = new BlankFragment4();
        transaction = fragmentManager.beginTransaction();
//当前的fragment会被myJDEditFragment替换
        transaction.replace(R.id.content_fl, blankFragment4);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
