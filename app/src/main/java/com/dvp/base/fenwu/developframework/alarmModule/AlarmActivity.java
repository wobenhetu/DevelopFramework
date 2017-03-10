package com.dvp.base.fenwu.developframework.alarmModule;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.dvp.base.fenwu.developframework.R;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity
{

    //private long timeRemain;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        startFirstAlarmClock(AlarmActivity.this);
    }

    /**
     * 首次启动的定时器
     * @param context
     */
    @TargetApi(19)
    public void startFirstAlarmClock(Context context)
    {
        Intent intent = new Intent(context, MyAlarmReceiver.class);
        intent.setAction("firstAlarm");
        // FLAG_UPDATE_CURRENT：如果PendingIntent已经存在，保留它并且只替换它的extra数据。
        // FLAG_CANCEL_CURRENT：如果PendingIntent已经存在，那么当前的PendingIntent会取消掉，然后产生一个新的PendingIntent。
        PendingIntent pi = PendingIntent.getBroadcast(context,
                1000, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 3);  //設置n秒后的執行
        // 设置闹钟
        // 当前版本为19（4.4）或以上使用精准闹钟
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else
        {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }
    }


    /**
     * 开启指定时间的闹钟
     */
    @TargetApi(19)
    public static void startAlarmClock(Context context, int hour, int minute)
    {

        Intent intent = new Intent(context, MyAlarmReceiver.class);
        intent.setAction("startalarm");
        intent.putExtra("hour", hour);
        intent.putExtra("minute",minute);
        // FLAG_UPDATE_CURRENT：如果PendingIntent已经存在，保留它并且只替换它的extra数据。
        // FLAG_CANCEL_CURRENT：如果PendingIntent已经存在，那么当前的PendingIntent会取消掉，然后产生一个新的PendingIntent。
        PendingIntent pi = PendingIntent.getBroadcast(context,
                1000, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //设置指定时间执行
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);

        //设置在多少时间后执行
        //calendar.add(Calendar.SECOND, 10);  //設置n秒后的執行
        //calendar.add(Calendar.MINUTE,1);//设置一分钟后执行
        calendar.add(Calendar.DAY_OF_MONTH, 1);//设置一天后执行
        // 设置闹钟
        // 当前版本为19（4.4）或以上使用精准闹钟
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else
        {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }

    }

    /**
     * 开启倒计时
     *
     * @param context    context
     * @param timeRemain 剩余时间
     */
    public static void startAlarmTimer(Context context, long timeRemain)
    {
        Intent intent = new Intent(context, MyAlarmReceiver.class);
        intent.setAction("startalarm");
        PendingIntent pi = PendingIntent.getActivity(context,
                1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        long countdownTime = timeRemain + SystemClock.elapsedRealtime();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, countdownTime, pi);
        } else
        {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, countdownTime, pi);
        }
    }

    /*


     /**
         * 取消闹钟
         *
         * @param context        context
         * @param alarmClockCode 闹钟启动code
         */
    public static void cancelAlarmClock(Context context, int alarmClockCode)
    {
        Intent intent = new Intent(context, MyAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, alarmClockCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context
                .getSystemService(Activity.ALARM_SERVICE);
        am.cancel(pi);
    }


    /**
     * 取得下次响铃时间
     *
     * @param hour   小时
     * @param minute 分钟
     * @return 下次响铃时间
     */
    public static long calculateNextTime(int hour, int minute)
    {
        // 当前系统时间
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 下次响铃时间
        long nextTime = calendar.getTimeInMillis();
        // 当单次响铃时
        // 当设置时间大于系统时间时
       /* if (nextTime > now)
        {
            return nextTime;
        } else
        {
            // 设置的时间加一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            nextTime = calendar.getTimeInMillis();
            return nextTime;
        }*/
        calendar.add(Calendar.MINUTE,1);
        nextTime = calendar.getTimeInMillis();
        return nextTime;
    }
}
