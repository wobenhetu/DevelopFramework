package com.dvp.base.fenwu.developframework.dagger2Module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/9/18.
 */

@Module
public class MainModule
{
    @Provides
    public Gson provideGson()
    {
        return  new Gson();
    }
}
