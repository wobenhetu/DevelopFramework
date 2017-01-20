package com.dvp.base.fenwu.developframework.common;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 作者：Administrator on 2016/10/25 13:35
 * <p>
 * 功能描述:DevelopFramework
 */
public class CommomApp extends TinkerApplication
{
    public CommomApp() {
        super(
                //tinkerFlags, which types is supported
                //dex only, library only, all support
                ShareConstants.TINKER_ENABLE_ALL,
                // This is passed as a string so the shell application does not
                // have a binary dependency on your ApplicationLifeCycle class.
                "tinker.sample.android.app.SampleApplicationLike");
    }

    protected CommomApp(int tinkerFlags)
    {
        super(tinkerFlags);
    }

    protected CommomApp(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag)
    {
        super(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag);
    }

    protected CommomApp(int tinkerFlags, String delegateClassName)
    {
        super(tinkerFlags, delegateClassName);
    }


}


