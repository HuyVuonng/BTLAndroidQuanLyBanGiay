package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiaytablet.Class.TaiKhoan;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class DangNhap_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    EditText tenDN,mk;
    TextView quenMK;
    Button dangky,dangnhap;
    Intent intent;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    String user,pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        taiKhoanArrayList= new ArrayList<>();
        //Tao DB
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS User (ID INTEGER PRIMARY KEY AUTOINCREMENT,TenDN VARCHAR(50),MatKhau VARCHAR (25),HoTen NVARCHAR(50),Trangthai INTEGER)");

        database.QuerryData("UPDATE User SET Trangthai=0");
//        database.QuerryData("INSERT INTO User VALUES(null,'huy','1234567')");
        anhxa();
        user = getIntent().getStringExtra("userreg");
        tenDN.setText(user);
        pass = getIntent().getStringExtra("passreg");
        mk.setText(pass);
        getdata();


        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DangNhap_Activity.this, DangKy_Activity.class);
                startActivity(intent);
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mk1=mk.getText().toString();
                String tenDN1= tenDN.getText().toString();
                if(tenDN.equals("")||mk.equals("")){
                    Toast.makeText(DangNhap_Activity.this, "Vui l??ng ??i???n t???t c??? th??ng tin", Toast.LENGTH_SHORT).show();

                }
                else{
                    boolean trangthaitaikhoan=false;
                    for(int i =0; i< taiKhoanArrayList.size(); i++){
                        if(tenDN1.equals(taiKhoanArrayList.get(i).getUsername()) && mk1.equals(taiKhoanArrayList.get(i).getPassword())){
                            database.QuerryData("UPDATE User SET Trangthai=1 WHERE TenDN='"+tenDN1+"'");
                            Intent intent = new Intent(DangNhap_Activity.this,MainActivity.class);
                            intent.putExtra("TenChuTaiKhoan", taiKhoanArrayList.get(i).getName());
                            startActivity(intent);

                            trangthaitaikhoan=true;
                            break;
                        }
                        else{
                            trangthaitaikhoan=false;
                        }
                    }
                    if(trangthaitaikhoan==true){
                        Toast.makeText(DangNhap_Activity.this, "????ng nh???p th??nh c??ng", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(DangNhap_Activity.this, "Ch??a c?? t??i kho???n n??y ho???c sai m???t kh???u", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        quenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DangNhap_Activity.this,QuenMK_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Cursor dataTaiKhoan = database.GetData("SELECT * FROM User ");
        taiKhoanArrayList.clear();
        while (dataTaiKhoan.moveToNext()) {
            int id= dataTaiKhoan.getInt(0);
            String TenDN = dataTaiKhoan.getString(1);
            String MatKhau = dataTaiKhoan.getString(2);
            String hoTen= dataTaiKhoan.getString(3);
            taiKhoanArrayList.add(new TaiKhoan(id,TenDN,MatKhau,hoTen));
        }
    }

    private void anhxa() {
        tenDN=findViewById(R.id.editTextTenDN);
        mk=findViewById(R.id.editTextMatKhauDN);
        dangky=findViewById(R.id.btnDangKy);
        dangnhap=findViewById(R.id.btnDangNhap);
        quenMK=findViewById(R.id.tvquenmk);
    }


}
