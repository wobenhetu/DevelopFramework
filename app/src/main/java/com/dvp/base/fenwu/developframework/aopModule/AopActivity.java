package com.dvp.base.fenwu.developframework.aopModule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dvp.base.fenwu.developframework.R;

public class AopActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);

        init();
    }
    private void init()
    {
        String s = null;
        int len = s.length();
        System.out.println(""+len);
    }
}
