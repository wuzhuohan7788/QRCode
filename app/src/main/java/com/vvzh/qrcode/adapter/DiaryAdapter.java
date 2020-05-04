package com.vvzh.qrcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vvzh.qrcode.R;
import com.vvzh.qrcode.bean.Diary;
import com.vvzh.qrcode.view.DiaryActivity;

import java.util.List;

public class DiaryAdapter extends BaseAdapter {

    private List<Diary> diaryList;
    private Context context;

    public DiaryAdapter(List<Diary> diaryList, Context context) {
        this.diaryList = diaryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return diaryList.size();
    }

    @Override
    public Object getItem(int i) {
        return diaryList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Diary diary = diaryList.get(position);
        View view;
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.result_item, viewGroup, false);
            holder.tv_result = view.findViewById(R.id.tv_result);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_result.setText(diary.getScan_result());

        return view;
    }

    class ViewHolder {
        TextView tv_result;
    }
}
