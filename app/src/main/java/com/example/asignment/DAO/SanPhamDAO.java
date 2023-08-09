package com.example.asignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asignment.DbHelper;
import com.example.asignment.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {

    DbHelper dbHelper;
    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPham> getDs(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SanPham", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }

        return list;
    }


    // them san pham
    public boolean themSanPham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("giaban", sanPham.getGiaban());
        contentValues.put("soluong", sanPham.getSoluong());

        long check = sqLiteDatabase.insert("SanPham", null, contentValues);
        if (check == -1) return false;
        else return true;
    }

    // update san pham
    public boolean UpdateSanPham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("giaban", sanPham.getGiaban());
        contentValues.put("soluong", sanPham.getSoluong());

        int check = sqLiteDatabase.update("SanPham", contentValues, "masp = ?", new String[]{String.valueOf(sanPham.getMasp())});

        if (check == 0) return false;
        return true;
    }

    public boolean DeleteSanPham(int masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        int check = sqLiteDatabase.delete("SanPham", "id =?", new String[]{String.valueOf(masp)});

        if (check == 0) return false;
        return true;
    }
}
