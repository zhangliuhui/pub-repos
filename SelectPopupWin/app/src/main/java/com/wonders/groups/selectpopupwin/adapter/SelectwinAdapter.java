package com.wonders.groups.selectpopupwin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wonders.groups.selectpopupwin.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class SelectwinAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<String> list;

    public SelectwinAdapter(Context context, List<String> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.selectitem, null);
        }
        TextView tv = (TextView)convertView.findViewById(R.id.selectListItem);
        tv.setText(list.get(position));
        return convertView;
    }
}
