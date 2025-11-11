package com.xxs.order.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.xxs.order.R;
import com.xxs.order.activity.user.RegisterUserActivity;
import com.xxs.order.util.FileImgUtil;

public class DBUtil extends SQLiteOpenHelper {

    private static int version = 1;
    private static String databaseName = "db_takeaway.db";

    private Context context;

    public static SQLiteDatabase con;

    public DBUtil(@Nullable Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys = false");

        db.execSQL("drop table if exists d_user");
        db.execSQL("create table d_user(s_id varchar(20) primary key," +
                "s_pwd varchar(20)," +
                "s_name varchar(20)," +
                "s_sex varchar(2)," +
                "s_address varchar(200)," +
                "s_phone varchar(20)," +
                "s_img varchar(255))");
        db.execSQL("INSERT INTO d_user (s_id,s_pwd,s_name,s_sex,s_address,s_phone,s_img)" +
                "VALUES (?,?,?,?,?,?,?)",new Object[]{"root","123456","小明杂货铺","男","北京","123123",""});

        db.execSQL("PRAGMA foreign_keys = true");

        db.execSQL("PRAGMA foreign_keys = false");

        db.execSQL("drop table if exists d_business");
        db.execSQL("create table d_business(s_id varchar(20) primary key," +
                "s_pwd varchar(20)," +
                "s_name varchar(20)," +
                "s_description varchar(200)," +
                "s_type varchar(20)," +
                "s_img varchar(255))");
        Drawable defaultDrawable = ContextCompat.getDrawable(context, R.drawable.upimg);
        Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();
        String path = FileImgUtil.getImageName();
        FileImgUtil.saveBitmapAsync(bitmapDef, path);

        db.execSQL("INSERT INTO d_business (s_id,s_pwd,s_name,s_description,s_type,s_img)" +
                "VALUES (?,?,?,?,?,?)",new Object[]{"root","123456","小明杂货铺","卖一下玩具","玩具店",""});

        db.execSQL("PRAGMA foreign_keys = true");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
