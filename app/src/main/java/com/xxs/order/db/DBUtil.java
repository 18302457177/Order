package com.xxs.order.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUtil extends SQLiteOpenHelper {

    private static int version = 2;
    private static String databaseName = "db_takeaway.db";

    private Context context;

    public static SQLiteDatabase con;

    public DBUtil(@Nullable Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = false");

        db.execSQL("drop table if exists d_business");
        db.execSQL("create table d_business(s_id varchar(20) primary key," +
                "s_pwd varchar(20)," +
                "s_name varchar(20)," +
                "s_description varchar(200)," +
                "s_type varchar(20)," +
                "s_img varchar(255))");
        db.execSQL("INSERT INTO d_business (s_id,s_pwd,s_name,s_description,s_type,s_img)" +
                "VALUES (?,?,?,?,?,?)",new Object[]{"root","123456","小明杂货铺","卖一下玩具","玩具店",""});

        db.execSQL("PRAGMA foreign_keys = true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
