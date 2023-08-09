package com.example.asignment;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "QLNguoiDung", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable_NguoiDung = "CREATE TABLE NguoiDung(tendangnhap TEXT PRIMARY KEY, matkhau TEXT, hoten TEXT)";
        db.execSQL(createTable_NguoiDung);
        String addData_NguoiDung = "INSERT INTO NguoiDung VALUES('Cam', 'Cam1', 'Nguyen Hung')";
        db.execSQL(addData_NguoiDung);

        String createTable_SanPham = "CREATE TABLE SanPham(masp INTEGER PRIMARY KEY AUTOINCREMENT, tensp TEXT, giaban INTEGER, soluong INTEGER)";
        db.execSQL(createTable_SanPham);
        String addData_SanPham = "INSERT INTO SanPham VALUES('1', 'banh', '10000', '5'), (2, 'keo', '5000', '3')";
        db.execSQL(addData_SanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("DROP TABLE IF EXISTS NguoiDung");
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
        }
    }
}
