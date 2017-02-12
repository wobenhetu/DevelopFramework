package com.dvp.base.fenwu.developframework.constraintLayoutModule;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Test1Activity extends AppCompatActivity
{

    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.button5)
    Button button5;
    @Bind(R.id.lay_root)
    ConstraintLayout layRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ButterKnife.bind(this);
    }
}
