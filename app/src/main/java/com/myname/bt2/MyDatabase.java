package com.myname.bt2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase {
    private SQLiteDatabase db;

    public MyDatabase(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<NhanVien> getNhanVienByPhongBan(String maPhongBan) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = db.query("NhanVien", null, "maPhongBan = ?", new String[]{maPhongBan}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") NhanVien nhanVien = new NhanVien(
                        cursor.getString(cursor.getColumnIndexOrThrow("maNV")),
                        cursor.getString(cursor.getColumnIndexOrThrow("maPhongBan")),
                        cursor.getString(cursor.getColumnIndexOrThrow("tenNV")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("tuoi"))
                );
                nhanVienList.add(nhanVien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return nhanVienList;
    }

    // Thêm
    public long addNhanVien(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put("maNv", nhanVien.getMaNV());
        values.put("maPhongBan", nhanVien.getMaPhongBan());
        values.put("tenNV", nhanVien.getTenNV());
        values.put("tuoi", nhanVien.getTuoi());

        return db.insert("NhanVien", null, values);
    }
    // Xóa
    public int deleteNhanVien(String maNV) {
        return db.delete("NhanVien", "maNv = ?", new String[]{maNV});
    }
//    // Sửa
//    public int updateNhanVien(NhanVien nhanVien) {
//        ContentValues values = new ContentValues();
//        values.put("maPhongBan", nhanVien.getMaPhongBan());
//        values.put("tenNV", nhanVien.getTenNV());
//        values.put("tuoi", nhanVien.getTuoi());
//
//        return db.update("NhanVien", values, "maNV = ?", new String[]{nhanVien.getMaNV()});
//    }
    // Truy vấn dữ liệu
//    public List<NhanVien> getAllNhanVien() {
//        List<NhanVien> nhanVienList = new ArrayList<>();
//        Cursor cursor = db.query("NhanVien", null, null, null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                NhanVien nhanVien = new NhanVien(
//                        cursor.getString(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getInt(3)
//                );
//                nhanVienList.add(nhanVien);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return nhanVienList;
//    }

    // Thêm
    public long addPhongBan(PhongBan phongBan) {
        ContentValues values = new ContentValues();
        values.put("maPhongBan", phongBan.getMaPhongBan());
        values.put("tenPhongBan", phongBan.getTenPhongBan());
        values.put("soPhongBan", phongBan.getSoPhongBan());

        return db.insert("PhongBan", null, values);
    }
//    // Xóa
//    public int deletePhongBan(String maPhongBan) {
//        return db.delete("PhongBan", "maPhongBan = ?", new String[]{maPhongBan});
//    }
//    // Sửa
//    public int updatePhongBan(PhongBan phongBan) {
//        ContentValues values = new ContentValues();
//        values.put("tenPhongBan", phongBan.getTenPhongBan());
//        values.put("soPhongBan", phongBan.getSoPhongBan());
//
//        return db.update("PhongBan", values, "maPhongBan = ?", new String[]{phongBan.getMaPhongBan()});
//    }
    // Truy vấn dữ liệu
    public List<PhongBan> getAllPhongBan() {
        List<PhongBan> phongBanList = new ArrayList<>();
        Cursor cursor = db.query("PhongBan", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                PhongBan phongBan = new PhongBan(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                );
                phongBanList.add(phongBan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return phongBanList;
    }

    // Truy vấn Số Nhân Viên trong Phòng Ban
    public int getNhanVienCountByPhongBan(String maPhongBan) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM NhanVien WHERE maPhongBan = ?", new String[]{maPhongBan});
        if (cursor != null && cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        }
        return 0;
    }

}

