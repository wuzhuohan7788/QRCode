package com.vvzh.qrcode.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.vvzh.qrcode.adapter.DiaryAdapter;
import com.vvzh.qrcode.bean.Diary;

import com.vvzh.qrcode.R;
import com.vvzh.qrcode.db.DatabaseManager;

public class DiaryActivity extends AppCompatActivity {
    ListView diary_lv_list;
    DatabaseManager dbManager;
    DiaryAdapter adapter;
    private List<Diary> diaryList = new ArrayList<>();
    private static final String TAG = "DiaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        dbManager = DatabaseManager.getInstance(this);
        initView();
    }

    private void initView() {
        diary_lv_list = findViewById(R.id.diary_lv_dataList);
        diaryList.addAll(dbManager.queryAllResult());
        adapter = new DiaryAdapter(diaryList, DiaryActivity.this);
        diary_lv_list.setAdapter(adapter);
    }
}
