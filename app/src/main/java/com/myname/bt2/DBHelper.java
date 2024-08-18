package com.myname.bt2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuanLyNhanVien.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableNhanVien = "CREATE TABLE NhanVien (" +
                "maNv TEXT PRIMARY KEY AUTOINCREMENT, " +
                "maPhongBan TEXT, " +
                "tenNV TEXT, " +
                "tuoi INTEGER)";

        String createTablePhongBan = "CREATE TABLE PhongBan (" +
                "maPhongBan TEXT PRIMARY KEY AUTOINCREMENT, " +
                "tenPhongBan TEXT, " +
                "soPhongBan INTEGER)";

        db.execSQL(createTableNhanVien);
        db.execSQL(createTablePhongBan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS PhongBan");
        onCreate(db);
    }
}

