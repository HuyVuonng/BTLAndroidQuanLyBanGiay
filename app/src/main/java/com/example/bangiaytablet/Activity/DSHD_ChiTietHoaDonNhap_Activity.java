package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bangiaytablet.Adapter.DS_hoa_don_nhap_Adapter;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class DSHD_ChiTietHoaDonNhap_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    DS_hoa_don_nhap_Adapter adapter;
    ArrayList<HoaDonNhap> arrayList;
    Intent intent;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dshd_chi_tiet_hoa_don_nhap);

        lv = findViewById(R.id.lv_DSHD_ChitietHDnhap);
        arrayList = new ArrayList<>();
        adapter = new DS_hoa_don_nhap_Adapter(this,R.layout.dong_ds_hd_nhap,arrayList);
        lv.setAdapter(adapter);


        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String maHD=Integer.toString(arrayList.get(i).getMaHoaDon());
                intent =new Intent(DSHD_ChiTietHoaDonNhap_Activity.this,DSSP_ChiTietHoaDonNhap_Activity.class);
                intent.putExtra("maHDNhap",maHD);
                startActivity(intent);
                Toast.makeText(DSHD_ChiTietHoaDonNhap_Activity.this,maHD,Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void hienthiDL() {
        Cursor dataHDNHap = database.GetData("SELECT * FROM HoaDonNhap order by maHD DESC");
        Cursor dataChitietHoadonNhap = database.GetData("SELECT maHDNhap, GiaNhap,SlNhap ,maHangNhap FROM ChiTietHoaDonNhap order by maHDNhap DESC");
        arrayList.clear();
        while (dataHDNHap.moveToNext()) {
            Double TONGTIENNHAP=0.0;
            Double TONGTIENMATHANG=0.0;
            String nhaCUngCap= dataHDNHap.getString(3);
            String nguoiNhap=dataHDNHap.getString(2);
            int maHD = dataHDNHap.getInt(0);
            String ngaytaoHD = dataHDNHap.getString(1);
            while(dataChitietHoadonNhap.moveToNext()){
                int maHDCHitiet=dataChitietHoadonNhap.getInt(0);
                if(maHDCHitiet==maHD){
                    TONGTIENMATHANG=dataChitietHoadonNhap.getDouble(1)*dataChitietHoadonNhap.getInt(2);;
                    TONGTIENNHAP+=TONGTIENMATHANG;

                }
            }
            dataChitietHoadonNhap.moveToFirst();
            arrayList.add(new HoaDonNhap(ngaytaoHD,nguoiNhap,nhaCUngCap,maHD,TONGTIENNHAP));

        }
//S???p x???p t???ng ti???n gi???m d???n
//        for(int i=0;i<arrayList.size();i++){
//            for(int j=i+1;j<arrayList.size();j++){
//                if(arrayList.get(i).getTongtien() < arrayList.get(j).getTongtien()){
//                    HoaDonNhap tg1;
//                    tg1=arrayList.get(i);
//                    arrayList.set(i,arrayList.get(j));
//                    arrayList.set(j,tg1);
//                }
//            }
//        }

        adapter.notifyDataSetChanged();
    }
}