package com.xxs.order.dao;

import android.database.sqlite.SQLiteDatabase;

import com.xxs.order.db.DBUtil;

public class AdminDao {

    public static SQLiteDatabase db = DBUtil.con;

    public static int saveBusinessUser(String id,String pwd,String name,String des,String type,String tx){
        String data[] = {id,pwd,name,des,type,tx};
        try{
            db.execSQL("INSERT INTO d_business (s_id,s_pwd,s_name,s_description,s_type,s_img)" +
                    "VALUES (?,?,?,?,?,?)",data);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    public static int saveCommonUser1(String id,String pwd,String name,String sex,String address,String phone,String tx){
        String data[] = {id,pwd,name,sex,address,phone,tx};
        try{
            db.execSQL("INSERT INTO d_user (s_id,s_pwd,s_name,s_sex,s_address,s_phone,s_img)" +
                    "VALUES (?,?,?,?,?,?,?)",data);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
