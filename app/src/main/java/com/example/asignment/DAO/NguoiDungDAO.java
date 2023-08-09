package com.example.asignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asignment.DbHelper;

public class NguoiDungDAO {
    DbHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // login
    public boolean CheckLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NguoiDung WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    // register
    public boolean Register(String username, String password, String hoTen) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", password);
        contentValues.put("hoten", hoTen);

        long check = sqLiteDatabase.insert("NguoiDung", null, contentValues);
        if (check != -1) return true;
        else return false;

    }
}
