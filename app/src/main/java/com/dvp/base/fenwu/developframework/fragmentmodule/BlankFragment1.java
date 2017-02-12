package com.dvp.base.fenwu.developframework.fragmentmodule;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dvp.base.adapter.listviewadapter.BaseAdapterHelper;
import com.dvp.base.adapter.listviewadapter.QuickAdapter;
import com.dvp.base.fenwu.developframework.R;
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


    public BlankFragment1()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init()
    {

        manager = getFragmentManager();

        for (int i = 0; i < 10; i++)
        {
            mData.add("aaaaa" + i);
        }

        adapter = new QuickAdapter<String>(getActivity(), R.layout.item_list_aaa, mData)
        {
            @Override
            protected void convert(BaseAdapterHelper baseAdapterHelper, String o)
            {
                baseAdapterHelper.setText(R.id.tv, o.toString());
            }
        };
        listview.setAdapter(adapter);
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
