package com.textscanning.jh.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.exception.DbException;
import com.textscanning.jh.dao.DBHandler;
import com.textscanning.jh.objects.TextImageObject;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * * ┏┓      ┏┓
 * *┏┛┻━━━━━━┛┻┓
 * *┃          ┃
 * *┃          ┃
 * *┃ ┳┛   ┗┳  ┃
 * *┃          ┃
 * *┃    ┻     ┃
 * *┃          ┃
 * *┗━┓      ┏━┛
 * *  ┃      ┃
 * *  ┃      ┃
 * *  ┃      ┗━━━┓
 * *  ┃          ┣┓
 * *  ┃         ┏┛
 * *  ┗┓┓┏━━━┳┓┏┛
 * *   ┃┫┫   ┃┫┫
 * *   ┗┻┛   ┗┻┛
 * Created by Hua on 16/6/23.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;

    private List<TextImageObject> dataList;

    public ImageAdapter(Context context) {
        this.context = context;
        initDataList();
    }

    private void initDataList() {
        dataList = new ArrayList<>();
        try {
            dataList.addAll(DBHandler.getDbUtils(context).findAll(TextImageObject.class));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
