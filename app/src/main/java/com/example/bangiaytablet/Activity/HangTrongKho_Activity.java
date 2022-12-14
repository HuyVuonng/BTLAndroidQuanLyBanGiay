package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiaytablet.Adapter.Hang_Adapter;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class HangTrongKho_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    ArrayList<Hang> arrayList;
    Hang_Adapter adapter;
    EditText edtTimKiem;
    RadioButton tangdan,giamdan;
    Button btnTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_trong_kho);

        lv = findViewById(R.id.lvloaigiay);
        edtTimKiem=findViewById(R.id.edtTimKiem);
//        btnTim=findViewById(R.id.btnTimKiemhangTRongKho);
        tangdan=findViewById(R.id.rdoGiaTang);
        giamdan=findViewById(R.id.rdoGiaGiam);
        arrayList = new ArrayList<>();
        adapter = new Hang_Adapter(this,R.layout.dong_hang_trong_kho,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS Hang (MAHANG varchar(50) PRIMARY KEY, TENlOAIGIAY VARCHAR(200),TongSl INTEGER,Gia Float,HangSX VARCHAR(200),MauSac varchar(50),Size41 INTEGER,Size42 INTEGER,Size43 INTEGER)");

        //Them DULieu

//       database.QuerryData("INSERT INTO Hang VALUES('MH1','Gi??y th??? thao',5,10000,'ADIDAS','Den',1,1,3)");
//       database.QuerryData("INSERT INTO Hang VALUES('MH2','Gi??y NIKE',10,20000,'ADIDAS','Den',5,2,3)");

        hienthiDL();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String maHang=arrayList.get(i).getMaHang();
                intent =new Intent(HangTrongKho_Activity.this,LSCacLanNhapCuaSP_Activity.class);
                intent.putExtra("maHDHang",maHang);
                startActivity(intent);
                Toast.makeText(HangTrongKho_Activity.this,maHang,Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog= new Dialog(HangTrongKho_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_chitiethang);
                dialog.show();

                TextView tenSPCT= dialog.findViewById(R.id.tvTenHangChitietHang);
                TextView nhaSX= dialog.findViewById(R.id.tvHangSXChiTiet);
                TextView MauSacCT= dialog.findViewById(R.id.tvMauCHitiet);
                TextView SLSIze41ct= dialog.findViewById(R.id.tvSlSize41ChiTiet);
                TextView SLSIze42ct= dialog.findViewById(R.id.tvSlSize42ChiTiet);
                TextView SLSIze43ct= dialog.findViewById(R.id.tvSlSize43ChiTiet);
                TextView tongSLCT= dialog.findViewById(R.id.tvTongSLChiTiet);
                Button thoatCT=dialog.findViewById(R.id.btnDongCHiTiet);

                thoatCT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                String Ten=arrayList.get(i).getTenHang();
                String Mau=arrayList.get(i).getMausac();
                String HangSX=arrayList.get(i).getNhaSanXuat();
                int SLSize41=arrayList.get(i).getSize41();
                int SLSize42=arrayList.get(i).getSize42();
                int SLSize43=arrayList.get(i).getSize43();
                int TongSL=arrayList.get(i).getSoLuong();

                tenSPCT.setText(Ten);
                MauSacCT.setText("M??u s???c: "+ Mau);
                nhaSX.setText("H??ng: "+HangSX);
                SLSIze41ct.setText("SL Size 41: "+ Integer.toString(SLSize41));
                SLSIze42ct.setText("SL Size 41: "+ Integer.toString(SLSize42));
                SLSIze43ct.setText("SL Size 41: "+ Integer.toString(SLSize43));
                tongSLCT.setText("T???ng SL: "+ Integer.toString(TongSL));

                return true;
            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String NDTIM= edtTimKiem.getText().toString().trim();
                if(TextUtils.isEmpty(NDTIM)){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        String MauSac= dataHang.getString(5);
                        Double Gia=dataHang.getDouble(3);
                        arrayList.add(new Hang(MaHang,TenHang,MauSac,SL,Gia,dataHang.getBlob(9)));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                    return;
                }


                Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG Like '%"+NDTIM+"%' or TENlOAIGIAY Like '%"+NDTIM+"%' ");
                arrayList.clear();
                while (dataHang.moveToNext()) {
                    int SL = dataHang.getInt(2);
                    String TenHang = dataHang.getString(1);
                    String MaHang = dataHang.getString(0);
                    String MauSac= dataHang.getString(5);
                    Double Gia=dataHang.getDouble(3);
                    arrayList.add(new Hang(MaHang,TenHang,MauSac,SL,Gia,dataHang.getBlob(9)));
                }
                adapter.notifyDataSetChanged();
                adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                lv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        giamdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(giamdan.isChecked()){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang order by Gia DESC");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        String MauSac= dataHang.getString(5);
                        Double Gia=dataHang.getDouble(3);
                        arrayList.add(new Hang(MaHang,TenHang,MauSac,SL,Gia,dataHang.getBlob(9)));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                }
            }
        });

        tangdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tangdan.isChecked()){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang order by Gia ASC");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        String MauSac= dataHang.getString(5);
                        Double Gia=dataHang.getDouble(3);
                        arrayList.add(new Hang(MaHang,TenHang,MauSac,SL,Gia,dataHang.getBlob(9)));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                }
            }
        });
    }

    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            String MauSac= dataHang.getString(5);
            String hangSX=dataHang.getString(4);
            int SLSize41=dataHang.getInt(6);
            int SLSize42=dataHang.getInt(7);
            int SLSize43=dataHang.getInt(8);
            Double Gia=dataHang.getDouble(3);
            byte[] hinhanh=dataHang.getBlob(9);
            arrayList.add(new Hang(MaHang,TenHang,hangSX,MauSac,SLSize41,SLSize42,SLSize43,SL,Gia,dataHang.getBlob(9)));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogXoa(String tenSp,String maHang){
        Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa);
        dialog.show();

        TextView textXacNhanSPXoa= dialog.findViewById(R.id.XacNhanXoaTXT);

        Button btnXoa=dialog.findViewById(R.id.buttonXacNhanXoa);
        Button btnHuyXoa=dialog.findViewById(R.id.buttonHuy);


        textXacNhanSPXoa.setText("B???n c?? c???c ch???n mu???n x??a "+tenSp+"?");


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QuerryData("Delete from Hang WHERE MAHANG='"+maHang+"'");
                Toast.makeText(HangTrongKho_Activity.this,"X??a th??nh c??ng",Toast.LENGTH_LONG).show();
                dialog.dismiss();
//                intent= new Intent(HangTrongKho_Activity.this,HangTrongKho_Activity.class);
//                startActivity(intent);
                hienthiDL();
            }
        });


        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }
}