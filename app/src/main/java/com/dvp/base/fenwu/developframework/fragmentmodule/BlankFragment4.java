package com.dvp.base.fenwu.developframework.fragmentmodule;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvp.base.fenwu.developframework.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment4 extends Fragment
{


    public BlankFragment4()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment4, container, false);
    }

}
