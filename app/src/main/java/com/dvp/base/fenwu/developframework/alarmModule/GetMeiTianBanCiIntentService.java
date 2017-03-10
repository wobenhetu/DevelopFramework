package com.dvp.base.fenwu.developframework.alarmModule;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class GetMeiTianBanCiIntentService extends IntentService
{

    public GetMeiTianBanCiIntentService()
    {
        super("GetMeiTianBanCiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            final String action = intent.getAction();
            if(action.equals(""))
            {

            }
        }
    }
}
