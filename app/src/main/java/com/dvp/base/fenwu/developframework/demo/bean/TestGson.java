package com.dvp.base.fenwu.developframework.demo.bean;

import java.util.List;

/**
 * 作者：Administrator on 2016/10/26 09:36
 * <p>
 * 功能描述:DevelopFramework
 */
public class TestGson
{

    /**
     * code : 200
     * data : [{"name":"lixin","id":"123456"},{"name":"lixin","id":"123456"}]
     * msg : hahahaha
     */

    private String code;
    private String msg;
    /**
     * name : lixin
     * id : 123456
     */

    private List<DataEntity> data;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public List<DataEntity> getData()
    {
        return data;
    }

    public void setData(List<DataEntity> data)
    {
        this.data = data;
    }

    public static class DataEntity
    {
        private String name;
        private String id;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }
    }
}


