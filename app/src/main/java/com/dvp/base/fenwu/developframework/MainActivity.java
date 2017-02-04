package com.dvp.base.fenwu.developframework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dvp.base.fenwu.developframework.demo.bean.SUser;
import com.dvp.base.fenwu.developframework.demo.bean.TestBean;
import com.dvp.base.fenwu.developframework.demo.bean.TestGson;
import com.dvp.base.fenwu.developframework.demo.interfaces.ApiService;
import com.dvp.base.fenwu.developframework.downloadModule.DownLoadManagerActivity;
import com.dvp.base.fenwu.developframework.fragmentmodule.HomeActivity;
import com.dvp.base.fenwu.developframework.ui.activity.MultiFragmentActivity;
import com.dvp.base.fenwu.developframework.ui.activity.RxjavaTest1Activity;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.imageView3)
    ImageView imageView3;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.i("dfw===1==", String.valueOf(SUser.sUserId));
        SUser.sUserId = 2;
        Log.i("dfw===2==", String.valueOf(SUser.sUserId));

        //testGSON();
        testHandler();
        //testGlide();
    }

    private void testGlide()
    {
        Glide.with(MainActivity.this).load("http://hpw123.coding.me/img/avatar.png").into(imageView3);
    }
    private void testHandler()
    {
        handler = new MyHandler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("dfw==========dfasdfasdfasd");
                handler.sendEmptyMessage(0);
            }
        }, 3000);

       /* new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);


                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();*/
    }

    private class MyHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    textView2.setText("handler执行完毕了，哈哈哈");
                    break;
            }
        }
    }


    private void testGSON()
    {
        Gson gson = new Gson();

        String jsonString = "{\"code\":\"200\",\n" +
                " \"data\":[{\"name\":null,\"id\":\"123456\"},{\"name\":\"lixin\",\"id\":\"123456\"}],\n" +
                " \"data2\":[{\"name\":null,\"id\":\"123456\"},{\"name\":\"lixin\",\"id\":\"123456\"}],\n" +
                " \"msg\":\"hahahaha\"\n" +
                "}";

        String jsonString1 = "{\n" +
                "   \"id\":\"asdfasd\",\n" +
                "   \"name\":\"asdf\",\n" +
                "   \"nicheng\":\"asdfasdf\"\n" +
                "}";

        String jsonString2 = "[{\"id\":\"asdfasd12312\",\n" +
                "   \"name\":\"asdf\",\n" +
                "   \"nicheng\":\"asdfasdf\"\n" +
                "},\n" +
                "   {\"id\":\"asdfasd123\",\n" +
                "   \"name\":\"asdf\",\n" +
                "   \"nicheng\":\"asdfasdf\"\n" +
                "},\n" +
                "   {\"id\":\"asdfasd\",\n" +
                "   \"name\":\"asdf\",\n" +
                "   \"nicheng\":\"asdfasdf\"\n" +
                "},\n" +
                "    {\"id\":\"asdfasd23444\",\n" +
                "   \"name\":\"asdf\",\n" +
                "   \"nicheng\":\"asdfasdf\"\n" +
                "}]";

        String jsonString3 = "";

        TestGson testGson = gson.fromJson(jsonString2, TestGson.class);

        System.out.println("dfw===" + testGson.toString());

        if (testGson.getData().get(0).getName() != null)
        {
            textView2.setText(testGson.getData().get(0).getName().toString());
        } else
        {
            textView2.setText("该对象为空");
        }


    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        } else if (id == R.id.nav_gallery)
        {

        } else if (id == R.id.nav_slideshow)
        {

        } else if (id == R.id.nav_manage)
        {

        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.button)
    public void onClick()
    {
        String baseUrl = "http://120.27.27.2:8080/";
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建HomePageService实现类对象
        ApiService homePageService = retrofit.create(ApiService.class);
        Call<TestBean> listCall = homePageService.getIpInfo();
        //发起请求，回调在主线程中，可以直接更新UI
        listCall.enqueue(new Callback<TestBean>()
        {
            @Override
            public void onResponse(Call<TestBean> call, Response<TestBean> response)
            {
                Log.i("response", response.toString());
                textView2.setText(response.body().getData().toString());
                Gson gson = new Gson();
                TestBean testBean = gson.fromJson(response.body().getData().toString(), TestBean.class);
                System.out.println("dfw====" + testBean.toString());
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t)
            {
                Log.i("response", "dddd");
            }
        });


    }

    @OnClick(R.id.button2)
    public void onCallClick()
    {
        /*Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);*/

        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .playOn(findViewById(R.id.button2));
    }
}
