package vn.edu.tlu.m2151170579;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cafe.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableLoaiMon = "CREATE TABLE loaimon (" +
                "Maloai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenLoai VARCHAR(50)," +
                "MoTa TEXT" +
                ")";
        db.execSQL(createTableLoaiMon);

        String createTableMon = "CREATE TABLE mon (" +
                "MaMon INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenMon VARCHAR(100)," +
                "Maloai INTEGER," +
                "MoTa TEXT," +
                "DonGia REAL," +
                "Hinh VARCHAR(255)," +
                "FOREIGN KEY (Maloai) REFERENCES loaimon(Maloai)" +
                ")";
        db.execSQL(createTableMon);

        String createTableKhachHang = "CREATE TABLE khachhang (" +
                "MaKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenDN VARCHAR(20)," +
                "MatKhau VARCHAR(32)," +
                "HoTen VARCHAR(50)," +
                "Diachi VARCHAR(50)," +
                "DienThoai VARCHAR(11)," +
                "Email VARCHAR(30)" +
                ")";
        db.execSQL(createTableKhachHang);

        String createTableHoaDon = "CREATE TABLE hoadon (" +
                "MaHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NgayDat DATETIME," +
                "ThoiGianGiaoDuKien DATETIME," +
                "DiachiGiaoHang VARCHAR(255)," +
                "MaKH INTEGER," +
                "TinhTrang INTEGER," +
                "FOREIGN KEY (MaKH) REFERENCES khachhang(MaKH)" +
                ")";
        db.execSQL(createTableHoaDon);

        String createTableChiTietHD = "CREATE TABLE chitiethd (" +
                "SoDH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaMon INTEGER," +
                "MaHD INTEGER," +
                "SoLuong INTEGER," +
                "FOREIGN KEY (MaMon) REFERENCES mon(MaMon)," +
                "FOREIGN KEY (MaHD) REFERENCES hoadon(MaHD)" +
                ")";
        db.execSQL(createTableChiTietHD);

        ContentValues valuesMon = new ContentValues();
        valuesMon.put("TenMon", "AMERICANO");
        valuesMon.put("Maloai", 1);
        valuesMon.put("MoTa", "Cà phê đen nguyên chất");
        valuesMon.put("DonGia", 49000);
        db.insert("mon", null, valuesMon);

        valuesMon = new ContentValues();
        valuesMon.put("TenMon", "CAPPUCCINO");
        valuesMon.put("Maloai", 1);
        valuesMon.put("MoTa", "Cà phê Ý với lớp bọt sữa dày");
        valuesMon.put("DonGia", 45000);
        db.insert("mon", null, valuesMon);

        valuesMon = new ContentValues();
        valuesMon.put("TenMon", "ESPRESSO SỮA ĐÁ");
        valuesMon.put("Maloai", 1);
        valuesMon.put("MoTa", "Espresso kết hợp với sữa đá");
        valuesMon.put("DonGia", 39000);
        db.insert("mon", null, valuesMon);

        valuesMon = new ContentValues();
        valuesMon.put("TenMon", "CAFE ĐEN ĐÁ");
        valuesMon.put("Maloai", 1);
        valuesMon.put("MoTa", "Cà phê phin truyền thống");
        valuesMon.put("DonGia", 39000);
        db.insert("mon", null, valuesMon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS chitiethd");
        db.execSQL("DROP TABLE IF EXISTS hoadon");
        db.execSQL("DROP TABLE IF EXISTS khachhang");
        db.execSQL("DROP TABLE IF EXISTS mon");
        db.execSQL("DROP TABLE IF EXISTS loaimon");
        onCreate(db);
    }
    public List<Mon> getAllMon() {
        List<Mon> monList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mon", null);

        if (cursor.moveToFirst()) {
            do {
                int maMonIndex = cursor.getColumnIndex("MaMon");
                int tenMonIndex = cursor.getColumnIndex("TenMon");
                int donGiaIndex = cursor.getColumnIndex("DonGia");

                if (maMonIndex != -1 && tenMonIndex != -1 && donGiaIndex != -1) {
                    int maMon = cursor.getInt(maMonIndex);
                    String tenMon = cursor.getString(tenMonIndex);
                    float donGia = cursor.getFloat(donGiaIndex);

                    monList.add(new Mon(maMon, tenMon, donGia));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return monList;
    }

}
