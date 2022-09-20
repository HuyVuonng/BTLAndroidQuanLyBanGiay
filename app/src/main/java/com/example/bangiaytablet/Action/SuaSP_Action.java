package com.example.bangiaytablet.Action;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiaytablet.Activity.HangTrongKho_Activity;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

public class SuaSP_Action extends AppCompatActivity {

    Intent intent;
    String tenspsua,maspsua,size41,size42,size43;
    EditText edtTensp,edtSLsize41,edtSLsize42,edtSLsize43;
    Button btnThaydoi,btnHuy;
    DatabaseQuanLy database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sp);

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        tenspsua = getIntent().getStringExtra("tenSpSua");
        maspsua = getIntent().getStringExtra("maSpSua");
        size41 = getIntent().getStringExtra("size41");
        size42 = getIntent().getStringExtra("size42");
        size43 = getIntent().getStringExtra("size43");


        edtTensp=findViewById(R.id.editTextTenSanPhamSua);
        edtSLsize41=findViewById(R.id.edtsls41);
        edtSLsize42=findViewById(R.id.edtsls42);
        edtSLsize43=findViewById(R.id.edtsls43);

        btnThaydoi=findViewById(R.id.btnCapNhapSua);
        btnHuy=findViewById(R.id.btnHuySua);

        edtTensp.setText(tenspsua);
        edtSLsize41.setText(size41);
        edtSLsize42.setText(size42);
        edtSLsize43.setText(size43);



        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SuaSP_Action.this, HangTrongKho_Activity.class);
                startActivity(intent);
            }
        });

        btnThaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmoi = edtTensp.getText().toString().trim();
                String SLSIze41moi= edtSLsize41.getText().toString().trim();
                String SLSIze42moi= edtSLsize42.getText().toString().trim();
                String SLSIze43moi= edtSLsize43.getText().toString().trim();

//                boolean slDangSo=false;

//                Kiem tra số lượng có phải số không
//                try {
//                    Integer.parseInt(SLmoi);
//                    slDangSo=true;
//                }
//                catch (NumberFormatException e){
////
//                    slDangSo=false;
//                }

                if(TextUtils.isEmpty(tenmoi)||TextUtils.isEmpty(SLSIze41moi)||TextUtils.isEmpty(SLSIze42moi)||TextUtils.isEmpty(SLSIze43moi)){
                    Toast.makeText(SuaSP_Action.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    int SLSize41moiINT= Integer.parseInt(SLSIze41moi);
                    int SLSize42moiINT= Integer.parseInt(SLSIze42moi);
                    int SLSize43moiINT= Integer.parseInt(SLSIze43moi);
                    int TongSoSP=SLSize41moiINT+SLSize42moiINT+SLSize43moiINT;

                    database.QuerryData("UPDATE Hang SET TENlOAIGIAY='"+tenmoi+"',TongSl='"+TongSoSP+"',Size41='"+SLSize41moiINT+"',Size42='"+SLSize42moiINT+"',Size43='"+SLSize43moiINT+"' WHERE MAHANG='"+maspsua+"'");
                        Toast.makeText(SuaSP_Action.this,"Cập nhật thành công!!",Toast.LENGTH_LONG).show();
                        intent=new Intent(SuaSP_Action.this,HangTrongKho_Activity.class);
                        startActivity(intent);

                }
            }
        });

    }
}