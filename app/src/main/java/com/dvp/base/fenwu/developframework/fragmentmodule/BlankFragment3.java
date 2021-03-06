package com.dvp.base.fenwu.developframework.fragmentmodule;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dvp.base.fenwu.developframework.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment
{


    @Bind(R.id.btn_next)
    Button btnNext;

    // Fragment管理对象
    private FragmentManager manager;
    private FragmentTransaction ft;

    public BlankFragment3()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init()
    {
        manager = getFragmentManager();
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
        BlankFragment4 blankFragment4 = new BlankFragment4();
        ft = manager.beginTransaction();
//当前的fragment会被myJDEditFragment替换
        ft.replace(R.id.content_fl, blankFragment4);
        ft.addToBackStack(null);
        ft.commit();
    }
}
