package com.dvp.base.fenwu.developframework.fragmentmodule;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;

import com.dvp.base.adapter.listviewadapter.BaseAdapterHelper;
import com.dvp.base.adapter.listviewadapter.QuickAdapter;
import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.common.CommomApp;
import com.dvp.base.view.NestedListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment
{


    @Bind(R.id.listview)
    NestedListView listview;

    QuickAdapter adapter;
    @Bind(R.id.btn_next)
    Button btnNext;
    private List<String> mData = new ArrayList<>();

    // Fragment管理对象
    private FragmentManager manager;
    private FragmentTransaction ft;

    private int scrolledX;
    private int scrolledY;

    public BlankFragment1()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 20; i++)
        {
            mData.add("aaaaa" + i);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        ButterKnife.bind(this, view);

        //listview.scrollTo( CommomApp.getInstance().getAppConfig().getInt("x",0), CommomApp.getInstance().getAppConfig().getInt("y",0));
        //System.out.println("dfw===000x="+CommomApp.getInstance().getAppConfig().getInt("x",0));
        //System.out.println("dfw===000y="+CommomApp.getInstance().getAppConfig().getInt("y",0));
        init();
        return view;
    }

    private void init()
    {

        manager = getFragmentManager();

        adapter = new QuickAdapter<String>(getActivity(), R.layout.item_list_aaa, mData)
        {
            @Override
            protected void convert(BaseAdapterHelper baseAdapterHelper, String o)
            {
                baseAdapterHelper.setText(R.id.tv, o.toString());
            }
        };
        listview.setAdapter(adapter);
       /* listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            *//**
             * 滚动状态改变时调用
             *//*
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 不滚动时保存当前滚动到的位置
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

                        //scrolledX = listview.getScrollX();
                    CommomApp.getInstance().getAppConfig().setInt("x",listview.getScrollX());
                        //scrolledY = listview.getScrollY();
                    CommomApp.getInstance().getAppConfig().setInt("y",listview.getScrollY());
                }
            }

            *//**
             * 滚动时调用
             *//*
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });*/
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_next)
    public void onClick()
    {
        BlankFragment2 blankFragment2 = new BlankFragment2();
        ft = manager.beginTransaction();
//当前的fragment会被myJDEditFragment替换
        ft.replace(R.id.content_fl, blankFragment2);
        ft.addToBackStack(null);
        ft.commit();
    }
}
