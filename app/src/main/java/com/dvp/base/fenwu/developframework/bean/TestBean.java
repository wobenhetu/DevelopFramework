package com.dvp.base.fenwu.developframework.bean;

import java.util.List;

/**
 * 作者：Administrator on 2016/9/28 14:50
 * <p>
 * 功能描述:DevelopFramework
 */
public class TestBean
{

    /**
     * id : 2c908ab056923048015692518b030056
     * rcsValue : 2c908ab056923048015692518b030056
     * shuJMCh : 0——6岁书架
     * shuJDJ : 1
     * images : [{"id":"2c908ab0572111940157212fe6a70102","attachType":"1","attachName":"7-Panda Bear .jpg","fileName":"/uploads/shujia/c6ba02f3-46f7-49a0-af6c-a486d89feb86.jpg","physicalName":"c6ba02f3-46f7-49a0-af6c-a486d89feb86.jpg","uploadTime":"2016-09-13 09:36:09.0","size":"45632"}]
     * rcsKey : 2c908ab056923048015692518b030056
     * rcsField : id
     * attachment : []
     */

    private List<DataEntity> data;

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
        private String id;
        private String rcsValue;
        private String shuJMCh;
        private String shuJDJ;
        private String rcsKey;
        private String rcsField;
        /**
         * id : 2c908ab0572111940157212fe6a70102
         * attachType : 1
         * attachName : 7-Panda Bear .jpg
         * fileName : /uploads/shujia/c6ba02f3-46f7-49a0-af6c-a486d89feb86.jpg
         * physicalName : c6ba02f3-46f7-49a0-af6c-a486d89feb86.jpg
         * uploadTime : 2016-09-13 09:36:09.0
         * size : 45632
         */

        private List<ImagesEntity> images;
        private List<?> attachment;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getRcsValue()
        {
            return rcsValue;
        }

        public void setRcsValue(String rcsValue)
        {
            this.rcsValue = rcsValue;
        }

        public String getShuJMCh()
        {
            return shuJMCh;
        }

        public void setShuJMCh(String shuJMCh)
        {
            this.shuJMCh = shuJMCh;
        }

        public String getShuJDJ()
        {
            return shuJDJ;
        }

        public void setShuJDJ(String shuJDJ)
        {
            this.shuJDJ = shuJDJ;
        }

        public String getRcsKey()
        {
            return rcsKey;
        }

        public void setRcsKey(String rcsKey)
        {
            this.rcsKey = rcsKey;
        }

        public String getRcsField()
        {
            return rcsField;
        }

        public void setRcsField(String rcsField)
        {
            this.rcsField = rcsField;
        }

        public List<ImagesEntity> getImages()
        {
            return images;
        }

        public void setImages(List<ImagesEntity> images)
        {
            this.images = images;
        }

        public List<?> getAttachment()
        {
            return attachment;
        }

        public void setAttachment(List<?> attachment)
        {
            this.attachment = attachment;
        }

        public static class ImagesEntity
        {
            private String id;
            private String attachType;
            private String attachName;
            private String fileName;
            private String physicalName;
            private String uploadTime;
            private String size;

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public String getAttachType()
            {
                return attachType;
            }

            public void setAttachType(String attachType)
            {
                this.attachType = attachType;
            }

            public String getAttachName()
            {
                return attachName;
            }

            public void setAttachName(String attachName)
            {
                this.attachName = attachName;
            }

            public String getFileName()
            {
                return fileName;
            }

            public void setFileName(String fileName)
            {
                this.fileName = fileName;
            }

            public String getPhysicalName()
            {
                return physicalName;
            }

            public void setPhysicalName(String physicalName)
            {
                this.physicalName = physicalName;
            }

            public String getUploadTime()
            {
                return uploadTime;
            }

            public void setUploadTime(String uploadTime)
            {
                this.uploadTime = uploadTime;
            }

            public String getSize()
            {
                return size;
            }

            public void setSize(String size)
            {
                this.size = size;
            }
        }
    }
}


