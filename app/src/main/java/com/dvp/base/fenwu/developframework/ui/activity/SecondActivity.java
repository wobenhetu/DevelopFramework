package com.dvp.base.fenwu.developframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.bean.SUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity
{

    @Bind(R.id.button3)
    TextView button3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //测试多进程是否共享   发现每个进程都在一个虚拟机当中运行
        // SUser.sUserId = 2;
        Log.i("dfw===3==", String.valueOf(SUser.sUserId));

        button3.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                Log.i("dfw==touch===",motionEvent.toString());
                return false;
            }
        });


    }
}
