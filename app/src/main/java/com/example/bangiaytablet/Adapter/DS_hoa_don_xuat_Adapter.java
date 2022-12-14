package com.example.bangiaytablet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Class.HoaDonXuat;
import com.example.bangiaytablet.R;

import java.util.List;

public class DS_hoa_don_xuat_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HoaDonXuat> HoadonList;
    Intent intent;

    public DS_hoa_don_xuat_Adapter(Context context, int layout, List<HoaDonXuat> hoadonList) {
        this.context = context;
        this.layout = layout;
        HoadonList = hoadonList;
    }

    @Override
    public int getCount() {
        return HoadonList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView NgayXuat,maHD,tongtien,nguoimua,nguoiXuat;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;

        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.NgayXuat= view.findViewById(R.id.ngayXuatHang);
            viewHolder.maHD=view.findViewById(R.id.maHDXuat);
            viewHolder.tongtien=view.findViewById(R.id.tongsotienXuat);
            viewHolder.nguoimua=view.findViewById(R.id.nguoiMua);
            viewHolder.nguoiXuat=view.findViewById(R.id.nguoiXuatHang);

            view.setTag(viewHolder);



        }
        else{
            viewHolder= (ViewHolder)view.getTag();
        }
        HoaDonXuat HD= HoadonList.get(i);
        String maHD=Integer.toString(HD.getMaHoaDon());
        Double TongTienCuaHoaDon= HD.getTongtien();
        String tongTienString= Double.toString(TongTienCuaHoaDon);
        viewHolder.NgayXuat.setText("Ng??y t???o: "+HD.getNgayTaoHoaDon());
        viewHolder.maHD.setText("M?? HD: "+maHD);
        viewHolder.tongtien.setText("T???ng ti???n: "+tongTienString);
        viewHolder.nguoimua.setText("Ng?????i mua: "+HD.getNguoiMua());
        viewHolder.nguoiXuat.setText("Ng?????i nh???p: "+HD.getNguoiXuat());
        return view;
    }
}
