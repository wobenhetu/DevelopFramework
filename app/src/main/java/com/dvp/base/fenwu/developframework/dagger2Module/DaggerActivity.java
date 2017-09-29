package com.dvp.base.fenwu.developframework.dagger2Module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity
{

    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        // 使用Dagger2生成的类 生成组件进行构造，并注入
        DaggerMainComponent.builder()
                .build()
                .inject(this);

        initView();

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_poetry);
        String json = mGson.toJson(mPoetry);
        mTextView.setText(json);
    }
}
