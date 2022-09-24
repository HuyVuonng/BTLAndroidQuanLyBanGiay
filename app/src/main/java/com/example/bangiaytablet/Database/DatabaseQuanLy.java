package com.example.bangiaytablet.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseQuanLy extends SQLiteOpenHelper {
    public DatabaseQuanLy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Truy van khong tra kq
    public void QuerryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van tra kq

    public Cursor GetData(String sql){
        SQLiteDatabase database= getReadableDatabase();
        return database.rawQuery(sql,null);
    }


//    public void InsertNewProduct(String mahang,String tenhang,int soluong,double giaban,
//                            String hangSX, String MauSac,int slsize41,int slsize42,int slsize43,byte[] hinhanh){
//        SQLiteDatabase database= getWritableDatabase();
//        String sql="INSERT INTO Hang VALUES(?,?,?,?,?,?,?,?,?,?)";
//        SQLiteStatement statement=database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(0,mahang);
//        statement.bindString(1,tenhang);
//        statement.bindString(2, String.valueOf(soluong));
//        statement.bindString(3,mahang);
//        statement.bindString(4,mahang);
//        statement.bindString(5,mahang);
//        statement.bindString(6,mahang);
//        statement.bindString(7,mahang);
//        statement.bindString(8,mahang);
//        statement.bindBlob(9,hinhanh);
//
//
//
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
