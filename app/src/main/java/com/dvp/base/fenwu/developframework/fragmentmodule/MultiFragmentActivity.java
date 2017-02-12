package com.dvp.base.fenwu.developframework.fragmentmodule;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.fragmentmodule.BlankFragment1;
import com.dvp.base.fenwu.developframework.fragmentmodule.BlankFragment2;
import com.dvp.base.fenwu.developframework.fragmentmodule.BlankFragment3;
import com.dvp.base.fenwu.developframework.fragmentmodule.BlankFragment4;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MultiFragmentActivity extends AppCompatActivity
{

    @Bind(R.id.activity_multi_fragment)
    LinearLayout activityMultiFragment;
    @Bind(R.id.fragment_ll)
    LinearLayout fragmentLl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);
        ButterKnife.bind(this);


        BlankFragment1 fragment1 = new BlankFragment1();
        BlankFragment2 fragment2 = new BlankFragment2();
        BlankFragment3 fragment3 = new BlankFragment3();
        BlankFragment4 fragment4 = new BlankFragment4();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction.add(R.id.fragment_ll, fragment1);
        transaction.add(R.id.fragment_ll, fragment2);
        transaction.add(R.id.fragment_ll, fragment3);
        transaction.add(R.id.fragment_ll, fragment4);
        transaction.commit();
    }
}



