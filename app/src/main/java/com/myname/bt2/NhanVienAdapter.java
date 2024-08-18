package com.myname.bt2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    private final List<NhanVien> nhanVienList;
    private final LayoutInflater inflater;
    private final String tenPhongBan;

    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList, String tenPhongBan) {
        super(context, 0, nhanVienList);
        this.nhanVienList = nhanVienList;
        this.inflater = LayoutInflater.from(context);
        this.tenPhongBan = tenPhongBan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_nhan_vien, parent, false);
        }

        TextView tvItemNhanVien = convertView.findViewById(R.id.tvItemNhanVien);
        NhanVien nhanVien = nhanVienList.get(position);

        // Định dạng item theo yêu cầu
        String itemText = tenPhongBan + ": " + nhanVien.getMaNV()+ ": " + nhanVien.getTenNV() + ": " + nhanVien.getTuoi();
        tvItemNhanVien.setText(itemText);

        return convertView;
    }
}

