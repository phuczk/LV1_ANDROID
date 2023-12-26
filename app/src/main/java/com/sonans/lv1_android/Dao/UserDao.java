package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(User obj) {
        ContentValues values = new ContentValues();
        values.put("userName", obj.getUserName());
        values.put("hoTen", obj.getHoTen());
        values.put("password", obj.getPassword());
        return db.insert("user", null ,values);
    }

    public int update(User obj) {
        ContentValues values = new ContentValues();

        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getPassword());

        return db.update("user", values ,"userName=?", new String[]{String.valueOf(obj.getUserName())});
    }

    public int delete(String id) {
        return db.delete("user", "maUser=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<User> getData(String sql, String...selectionArgs) {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            User obj = new User();
            obj.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            Log.i("//=======", obj.toString());
            list.add(obj);
        }
        return list;
    }

    public List<User> getAll(){
        String sql = "SELECT * FROM user";
        return getData(sql);
    }

    public User getID(String id) {
        String sql = "SELECT * FROM user WHERE userName=?";
        List<User> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null; // hoặc xử lý theo nhu cầu của bạn nếu danh sách rỗng
        }
    }

    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM user WHERE userName=? AND password=?";
        List<User> list = getData(sql, id, password);
        if(list.size() == 0){
            return -1;
        }
        return 1;
    }
}
