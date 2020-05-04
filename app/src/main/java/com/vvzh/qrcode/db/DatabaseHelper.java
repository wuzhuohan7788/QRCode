package com.vvzh.qrcode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vvzh.qrcode.constant.Constant;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    //创建日志表
    private static final String CREATE_TABLE_DIARY = " create table " + Constant.TABLE_DIARY +
            "(" + Constant.TABLE_DIARY_ID + " integer primary key autoincrement, " +
            Constant.TABLE_DIARY_RESULT + " text)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TABLE_DIARY" + CREATE_TABLE_DIARY);
        onCreate(db);
    }
}
