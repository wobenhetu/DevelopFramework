package com.dvp.base.fenwu.developframework.common;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.dvp.base.app.APP;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 作者：Administrator on 2016/10/25 13:35
 * <p>
 * 功能描述:DevelopFramework
 */
public class CommomApp extends APP
{


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //方法数越界的处理
        MultiDex.install(this);
    }

}


