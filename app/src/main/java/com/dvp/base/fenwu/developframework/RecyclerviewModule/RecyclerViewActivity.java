package com.dvp.base.fenwu.developframework.RecyclerviewModule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.dvp.base.fenwu.developframework.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * recyclerview 原理链接
 * https://juejin.im/entry/586a12c5128fe10057037fba
 *
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0307/4032.html
 */
public class RecyclerViewActivity extends AppCompatActivity
{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerview.setLayoutManager(layoutManager);

        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerview.setAdapter(new MyAdapter(getData()));

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 10000; i++) {
            data.add(i + temp);
        }
        return data;
    }
}
