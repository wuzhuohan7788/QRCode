package com.vvzh.qrcode.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.vvzh.qrcode.bean.Diary;
import com.vvzh.qrcode.constant.Constant;

public class DatabaseManager {
    private static final String TAG = "DatabaseManager";

    private static DatabaseManager manager;
    private DatabaseHelper helper;
    private SQLiteDatabase sqldb;

    public DatabaseManager(Context context) {
        helper = new DatabaseHelper(context, Constant.DB_NAME, null, 1);
        sqldb = helper.getWritableDatabase();
        Log.i(TAG, "DatabaseManager: successful");
    }

    public static DatabaseManager getInstance(Context context) {
        if (manager == null) {
            manager = new DatabaseManager(context);
        }
        return manager;
    }

    //新增识别结果
    public void insertScanResult(String result) {
        ContentValues values = new ContentValues();
        values.put("result", result);
        sqldb.insert(Constant.TABLE_DIARY, null, values);

        Log.i(TAG, "insertScanResult: Successful");
    }

    //删除一条识别结果
    public void deleteScanResult(int id) {
        sqldb.execSQL("delete from " + Constant.TABLE_DIARY + "where " + Constant.TABLE_DIARY_ID + "=" + id);
        Log.i(TAG, "deleteScanResult: Successful");
    }

    //删除所有识别结果
    public void deleteAll() {
        sqldb.delete(Constant.TABLE_DIARY, null, null);
        Log.i(TAG, "deleteAll: Successful");
    }

    //查询所有识别结果
    public List<Diary> queryAllResult() {
        List<Diary> list = new ArrayList<>();
        Cursor cursor = sqldb.rawQuery("select * from " + Constant.TABLE_DIARY, null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Diary diary = new Diary();
                diary.setId(cursor.getInt(0));
                diary.setScan_result(cursor.getString(1));
                list.add(diary);
                Log.i(TAG, "queryAllResult: Successful");
            }
            cursor.close();
        } else {
            Log.i(TAG, "queryAllResult: no record");
        }

        return list;
    }
}
