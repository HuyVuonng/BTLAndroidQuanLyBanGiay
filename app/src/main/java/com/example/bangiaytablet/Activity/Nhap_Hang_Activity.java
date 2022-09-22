package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bangiaytablet.Action.Nhap_Hang_Action;
import com.example.bangiaytablet.Adapter.Hang_Adapter;
import com.example.bangiaytablet.Adapter.Nhap_Hang_Activity_Adapter;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class Nhap_Hang_Activity extends AppCompatActivity {

    DatabaseQuanLy database;
    ArrayList<HoaDonNhap> arrayList;
    Nhap_Hang_Activity_Adapter adapter;
    ArrayList<ChitietHoaDonNhap> arrayListChitietHoaDonNhapl;
    TextView maHDNhap,nguoiNhap,NhaCC,ngaytaoHD,tongtiencuahpadon;
    ImageView imgvThemSp;
    Intent intent;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang);

        lv= findViewById(R.id.lvNhungMatHangCotrongHoaDon);
        arrayList= new ArrayList<>();
        arrayListChitietHoaDonNhapl= new ArrayList<>();
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);
        adapter = new Nhap_Hang_Activity_Adapter(this,R.layout.dong_cac_san_pham_dang_nhap_trong_hoa_don,arrayListChitietHoaDonNhapl);
        lv.setAdapter(adapter);

        anhxa();
        getdataHoaDonNhap();
        getdataChiTietHoaDonNhap();
        int doDaiArray= arrayList.size();
        maHDNhap.setText("Mã hóa đơn: "+arrayList.get(doDaiArray-1).getMaHoaDon());
        nguoiNhap.setText("Người nhập: "+arrayList.get(doDaiArray-1).getNguoiNhap());
        NhaCC.setText("Nhà cung cấp: "+arrayList.get(doDaiArray-1).getNcc());
        ngaytaoHD.setText("Ngày nhập: "+arrayList.get(doDaiArray-1).getNgayTaoHoaDon());


        imgvThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(Nhap_Hang_Activity.this, Nhap_Hang_Action.class);
                startActivity(intent);
            }
        });
    }

    private void getdataHoaDonNhap() {
        Cursor dataHoaDonNhap = database.GetData("SELECT * FROM HoaDonNhap");
        arrayList.clear();
        while (dataHoaDonNhap.moveToNext()) {
            int maHDNhap= dataHoaDonNhap.getInt(0);
            String ngayTaoHD= dataHoaDonNhap.getString(1);
            String nguoiNhap=dataHoaDonNhap.getString(2);
            String nhacungcap=dataHoaDonNhap.getString(3);
            arrayList.add(new HoaDonNhap(ngayTaoHD,nguoiNhap,nhacungcap,maHDNhap));
        }
    }

    private void anhxa() {
        maHDNhap= findViewById(R.id.mahoadonnhap);
        nguoiNhap=findViewById(R.id.tvNguoiLapHoaDonNhap);
        NhaCC=findViewById(R.id.tvNhaCCHoaDOnNhap);
        ngaytaoHD=findViewById(R.id.NgaytaoHoaDonnhap);
        tongtiencuahpadon=findViewById(R.id.tvTongTientrongHoaDonNhap);
        imgvThemSp=findViewById(R.id.imageViewThemSPVaoHDNhap);
    }

    private void getdataChiTietHoaDonNhap() {
        Cursor dataChiTietHoaDonNhap = database.GetData("SELECT * FROM ChiTietHoaDonNhap Where maHDNhap='"+arrayList.get(arrayList.size()-1).getMaHoaDon()+"'");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang,tenSP,MauSac;
        int soLuongNhap,size;
        Double gianhap;
        arrayListChitietHoaDonNhapl.clear();
        while (dataChiTietHoaDonNhap.moveToNext()) {
           mahang=dataChiTietHoaDonNhap.getString(1);
           soLuongNhap=dataChiTietHoaDonNhap.getInt(2);
           gianhap=dataChiTietHoaDonNhap.getDouble(3);
           size=dataChiTietHoaDonNhap.getInt(4);
           tenSP=MauSac="";
           while (dataHang.moveToNext()){
               String mahangtrongkho=dataHang.getString(0);
               if(mahangtrongkho.equalsIgnoreCase(mahang)){
                   tenSP=dataHang.getString(1);
                   MauSac=dataHang.getString(5);
               }
           }
            dataHang.moveToFirst();
            arrayListChitietHoaDonNhapl.add(new ChitietHoaDonNhap(soLuongNhap,size,mahang,tenSP,MauSac,gianhap));
        }
        adapter.notifyDataSetChanged();
    }
}