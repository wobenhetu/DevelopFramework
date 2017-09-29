package com.dvp.base.fenwu.developframework.demo.interfaces;

import com.dvp.base.fenwu.developframework.demo.bean.TestBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 作者：Administrator on 2016/9/28 14:49
 * <p>
 * 功能描述:DevelopFramework
 */

public interface ApiService
{

    /**
     * get  方式无参数请求
     * @return
     */
    @GET("ad_abc/rest/shujia/")
    Call<TestBean> getIpInfo();


}
