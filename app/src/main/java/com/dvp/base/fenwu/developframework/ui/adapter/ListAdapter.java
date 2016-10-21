package com.dvp.base.fenwu.developframework.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dvp.base.fenwu.developframework.R;
import com.dvp.base.fenwu.developframework.bean.TestListBean;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 作者：Administrator on 2016/10/4 22:45
 * <p>
 * 功能描述:DevelopFramework
 */
public class ListAdapter extends BaseAdapter
{
    private Context context;

    private List<TestListBean> mList = new ArrayList<>();
    public ListAdapter(Context context, List<TestListBean> list)
    {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup)
    {

        ViewHoder viewHoder = null;
        if(convertView == null)
        {
            viewHoder = new ViewHoder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_list,viewGroup,false);
            viewHoder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            viewHoder.titleTv = (TextView) convertView.findViewById(R.id.title_tv);
            viewHoder.contentTv = (TextView)convertView.findViewById(R.id.content_tv);
            convertView.setTag(viewHoder);
        }
        else
        {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        TestListBean testListBean = mList.get(i);
        viewHoder.titleTv.setText(testListBean.getTitle());
        viewHoder.contentTv.setText(testListBean.getContent());
        Glide.with(context)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .bitmapTransform(/*new BlurTransformation(context, 25), */new CropCircleTransformation(context))
                .into(viewHoder.imageView);

        return convertView;
    }

    static class ViewHoder{
        ImageView imageView;
        TextView titleTv;
        TextView contentTv;
    }
}


