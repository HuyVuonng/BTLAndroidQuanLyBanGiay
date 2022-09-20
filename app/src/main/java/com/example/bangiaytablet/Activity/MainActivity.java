package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    DatabaseQuanLy database;
    RelativeLayout hangtrongkho,nhaphang,xuathang,hoadonnhap,hoadonxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hangtrongkho=findViewById(R.id.hangtrongkho);
        nhaphang=findViewById(R.id.nhaphang);
        xuathang=findViewById(R.id.xuathang);
        hoadonnhap=findViewById(R.id.hoadonnhap);
        hoadonxuat=findViewById(R.id.hoadonxuat);


        //Tao DB
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);


        //Tao bang
//        database.QuerryData("CREATE TABLE IF NOT EXISTS HoaDonNhap (maHD INTEGER PRIMARY KEY AUTOINCREMENT,NgayTao VARCHAR(50),NguoiNhap VARCHAR(50),NhaCungCap VARCHAR(50))");
//        database.QuerryData("CREATE TABLE IF NOT EXISTS HoaDonXuat (maHD INTEGER PRIMARY KEY AUTOINCREMENT,NgayTao VARCHAR(50))");
        database.QuerryData("CREATE TABLE IF NOT EXISTS Hang (MAHANG varchar(50) PRIMARY KEY, TENlOAIGIAY VARCHAR(200),TongSl INTEGER,Gia Double,HangSX VARCHAR(200),MauSac varchar(50),Size41 INTEGER,Size42 INTEGER,Size43 INTEGER)");
//        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonNhap (maHD INTEGER ,NgayTao VARCHAR(50),maHang VARCHAR(50),tenHang VARCHAR(50),Sl INTEGER,GiaNhap Float),NguoiNhap VARCHAR(50),NhaCungCap VARCHAR(50)");
//        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonXuat (maHD INTEGER ,NgayTao VARCHAR(50),maHang VARCHAR(50),tenHang VARCHAR(50),Sl INTEGER,GiaXuat Float)");

        hangtrongkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainActivity.this,HangTrongKho_Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hàng trong kho", Toast.LENGTH_LONG).show();
            }
        });

//        nhaphang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Dialog dialog= new Dialog(MainActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.dialog_themhoadon_nhap);
//                dialog.show();
//
//
//                EditText ngayTaoHoaDon= dialog.findViewById(R.id.editTextNgayLapHoaDonNhap);
//                EditText NguoiTaoHoaDon= dialog.findViewById(R.id.editTextNguoiLapHoaDonNhap);
//                EditText ncc= dialog.findViewById(R.id.editTextNhaCungCap);
//
//                Button btnTaoHoaDon= dialog.findViewById(R.id.buttonThemHoaDonNhap);
//                Button btnHuyTaoHoaDon= dialog.findViewById(R.id.buttonHuyTaoHoaDonNhap);
//
//
//
//                btnHuyTaoHoaDon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String ngayTaoNhap=ngayTaoHoaDon.getText().toString().trim();
//                        String nguoiTaoHoaDon= NguoiTaoHoaDon.getText().toString().trim();
//                        String NhaCungCap= ncc.getText().toString().trim();
//
//                        Date dateNhap=null,datehientai = null;
//                        Boolean isDate=false;
//                        if(TextUtils.isEmpty(ngayTaoNhap)){
//                            Toast.makeText(MainActivity.this, "Hãy nhập ngày tạo hóa đơn", Toast.LENGTH_SHORT).show();
//
//                        }
//                        if(ngayTaoNhap.split("\\/").length==3){
//                            String ngaycat[]=ngayTaoNhap.split("\\/");
//                            Integer ngay= Integer.parseInt(ngaycat[0]);
//                            Integer thang= Integer.parseInt(ngaycat[1]);
//                            if(ngay>31||thang>12){
//                                Toast.makeText(MainActivity.this, "Ngày nhập theo dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
//                            }
//
//                            else{
//                                try {
//                                    dateNhap = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(ngayTaoNhap);
//
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                };
//                                datehientai= new Date();
//
//
//                                if(dateNhap.after(datehientai)){
//                                    Toast.makeText(MainActivity.this, "Ngày nhập không được quá ngày hiện tại", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    database.QuerryData("INSERT INTO HoaDonNhap VALUES(null,'"+ngayTaoNhap+"','"+nguoiTaoHoaDon+"','"+NhaCungCap+"')");
//                                    Toast.makeText(MainActivity.this, "Tạo hóa đơn thành công", Toast.LENGTH_SHORT).show();
//                                    intent= new Intent(MainActivity.this,NhapHangActivity.class);
//                                    startActivity(intent);
//                                    dialog.dismiss();
//                                }
//                            }
//                        }
//                    }
//                });
//            }
//        });



//        xuathang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog dialog= new Dialog(MainActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.dialog_themhoadon_nhap);
//                dialog.show();
//
//
//                EditText ngayTaoHoaDon= dialog.findViewById(R.id.editTextNgayLapHoaDonNhap);
//                Button btnTaoHoaDon= dialog.findViewById(R.id.buttonThemHoaDonNhap);
//                Button btnHuyTaoHoaDon= dialog.findViewById(R.id.buttonHuyTaoHoaDonNhap);
//
//
//
//                btnHuyTaoHoaDon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String ngayTaoXuat=ngayTaoHoaDon.getText().toString().trim();
//                        Date dateXuat=null,datehientai = null;
//                        if(TextUtils.isEmpty(ngayTaoXuat)){
//                            Toast.makeText(MainActivity.this, "Hãy nhập ngày tạo hóa đơn", Toast.LENGTH_LONG).show();
//
//                        }
//
//                        if(ngayTaoXuat.split("\\/").length==3){
//                            String ngaycat[]=ngayTaoXuat.split("\\/");
//                            Integer ngay= Integer.parseInt(ngaycat[0]);
//                            Integer thang= Integer.parseInt(ngaycat[1]);
//                            if(ngay>31||thang>12){
//                                Toast.makeText(MainActivity.this, "Ngày nhập theo dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
//                            }
//
//                            else{
//                                try {
//                                    dateXuat = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(ngayTaoXuat);
//
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                };
//                                datehientai= new Date();
//
//
//                                if(dateXuat.after(datehientai)){
//                                    Toast.makeText(MainActivity.this, "Ngày nhập không được quá ngày hiện tại", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    database.QuerryData("INSERT INTO HoaDonXuat VALUES(null,'"+ngayTaoXuat+"')");
//                                    Toast.makeText(MainActivity.this, "Tạo hóa đơn thành công", Toast.LENGTH_LONG).show();
//                                    intent= new Intent(MainActivity.this,XuatKhoActivity.class);
//                                    startActivity(intent);
//                                    dialog.dismiss();
//                                }
//
//
//
//                            }
//                        }
//
//
//
//
//                    }
//                });
//                Toast.makeText(MainActivity.this, "Xuất hàng", Toast.LENGTH_SHORT).show();
//            }
//        });

//        hoadonnhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent= new Intent(MainActivity.this,QLHoaDonNhap.class);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Hóa đơn nhập", Toast.LENGTH_SHORT).show();
//            }
//        });

//        hoadonxuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent= new Intent(MainActivity.this,QLHoaDonXuat.class);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Hóa đơn xuất", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}