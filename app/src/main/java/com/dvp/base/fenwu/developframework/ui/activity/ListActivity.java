package com.dvp.base.fenwu.developframework.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.demo.bean.TestListBean;
import com.dvp.base.fenwu.developframework.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
{



    private ListView listview;
    private ListAdapter adapter;
    private List<TestListBean> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listview = (ListView)findViewById(R.id.listview);
        init();

    }
    private void init()
    {
        mDataList = new ArrayList<>();
        getData();

        adapter = new ListAdapter(ListActivity.this,mDataList);
        listview.setAdapter(adapter);

        //滚动到顶部
        //listview.setSelection(0);


        //滚动到底部
        //listview.setSelection(adapter.getCount() - 1);

        new MyTask(3000,3000).start();
    }
    private void getData()
    {
        for (int i = 0; i < 100; i++)
        {
            TestListBean testListBean = new TestListBean();
            testListBean.setTitle("学习标题  "+i);
            testListBean.setContent("学习内容  "+i);
            mDataList.add(testListBean);
        }
    }

    class MyTask extends CountDownTimer
    {


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyTask(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished)
        {

        }

        @Override
        public void onFinish()
        {
            listview.setSelection(adapter.getCount() - 1);
        }
    }
}
