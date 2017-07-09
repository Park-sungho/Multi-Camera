/**
 * This program is free software: 
 * Added by park Sung-ho
 */
package com.multicamera.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MultiCameraDatabaseHelper extends SQLiteOpenHelper {
    public static final String IDX = DBConstants.DBTable.IDX;
    public static final String TITLE = DBConstants.DBTable.TITLE;
    public static final String REG_DATE = DBConstants.DBTable.REG_DATE;
    public static final String URL = DBConstants.DBTable.URL;
    public static final String CONTENT_NAME = DBConstants.DBTable.CONTENT_NAME;
    public static final String MODIFY_DATE = DBConstants.DBTable.MODIFY_DATE;

    public static final String DB_FILE_NAME = DBConstants.DB_FILE_NAME;
    public static final String TABLE_NAME = DBConstants.TABLE_NAME;
    private final String[] PROJECTION = { CONTENT_NAME };

    private static final String CREATE_TABLE = "create table " + TABLE_NAME;

    private static final String TABLE_SORT = "_id integer primary key autoincrement, " + MODIFY_DATE;
    private static final String TABLE_DROP = "drop table if exists " + TABLE_NAME;

    private static MultiCameraDatabaseHelper mDBHelder;

    private MultiCameraDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    public static MultiCameraDatabaseHelper getInstance(Context context) {
        if (mDBHelder == null) {
            mDBHelder = new MultiCameraDatabaseHelper(context, DB_FILE_NAME, null, DBConstants.VERSION);
        }

        return mDBHelder;
    }

    public static MultiCameraDatabaseHelper getInstance() {
        return mDBHelder;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = CREATE_TABLE + " (" + TABLE_SORT + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    public void dropAndCreate() {
        String sqlCreate = CREATE_TABLE + " (" + TABLE_SORT + " text)";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(TABLE_DROP);
        db.execSQL(sqlCreate);
        db.close();

    }

    public void dropTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(TABLE_DROP);
        db.close();
    }

    public void insertMultiCameraItem(CameraMediaItem item) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MultiCameraDatabaseHelper.CONTENT_NAME, item.mContentName);
        db.insert(MultiCameraDatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    public int getCount() {
        int count = 0;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, PROJECTION, null, null, null, null, null);
        if (cursor == null) {
            return 0;
        }

        count = cursor.getCount();
        if (count <= 0) {
            cursor.close();
            return 0;
        }

        cursor.close();
        return count;
    }

    public void deleteMultiCameraItem(CameraMediaItem item) {
    }

    public int deleteMultiCameraAllItem(String tableName) {
        int removeCount = 0;
        SQLiteDatabase db = getReadableDatabase();

        try {
            removeCount = db.delete(tableName, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return removeCount;
    }

    public ArrayList<CameraMediaItem> getReadMultiCameraItem() {
        ArrayList<CameraMediaItem> multiCameraItemList = new ArrayList<CameraMediaItem>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, PROJECTION, null, null, null, null, null);
        if (cursor == null) {
            return multiCameraItemList;
        }

        if (cursor.getCount() <= 0) {
            cursor.close();
            return multiCameraItemList;
        }

        cursor.moveToFirst();

        do {
            CameraMediaItem multiCameraItem = new CameraMediaItem();
            multiCameraItem.mContentName = cursor.getString(0);
            multiCameraItemList.add(multiCameraItem);
        } while (cursor.moveToNext());

        cursor.close();

        return multiCameraItemList;
    }
}
