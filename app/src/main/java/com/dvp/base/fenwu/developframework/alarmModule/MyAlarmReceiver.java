package com.dvp.base.fenwu.developframework.alarmModule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyAlarmReceiver extends BroadcastReceiver
{
    public MyAlarmReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getAction().equals("startalarm")){
            Toast.makeText(context, "startalarm", Toast.LENGTH_SHORT).show();
            AudioPlayer.getInstance(context).vibrate();
            //再启动定时器
            AlarmActivity.startAlarmClock(context,7,30);
        }
        else if(intent.getAction().equals("firstAlarm"))
        {
            Toast.makeText(context, "firstAlarm", Toast.LENGTH_SHORT).show();

            //首次使用app，会获取一下当天的班次；紧接着会开启一个定时器，只要不确定退出登录，
            //再设置指定时间启动定时器用来获取每天的班次
            AlarmActivity.startAlarmClock(context,7,20);
        }
    }
}
