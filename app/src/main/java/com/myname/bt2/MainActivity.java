package com.myname.bt2;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spPhongBan;
    private ListView lvDanhSach;
    private EditText edtMaNV, edtTenNV, edtTuoi;
    private Button btnThem, btnXoa, btnOpenAct;

    private MyDatabase myDatabase;
    private List<NhanVien> nhanVienList;
//    private ArrayAdapter<NhanVien> nhanVienAdapter;
    private List<PhongBan> phongBanList;
    private ArrayAdapter<PhongBan> phongBanAdapter;
    private NhanVienAdapter nhanVienAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spPhongBan = findViewById(R.id.spPhongBan);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtTuoi = findViewById(R.id.edtTuoi);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnOpenAct = findViewById(R.id.btnOpenAct);

        myDatabase = new MyDatabase(this);

        // Tạo 3 phòng ban mẫu
//        myDatabase.addPhongBan(new PhongBan("PB01", "Phòng Kỹ Thuật", 1));
//        myDatabase.addPhongBan(new PhongBan("PB02", "Phòng Nhân Sự", 2));
//        myDatabase.addPhongBan(new PhongBan("PB03", "Phòng Kinh Doanh", 3));

        phongBanList = myDatabase.getAllPhongBan();
        phongBanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBanList);
        phongBanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPhongBan.setAdapter(phongBanAdapter);

        spPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadNhanVienList(phongBanList.get(position).getMaPhongBan());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lvDanhSach.setOnItemClickListener((parent, view, position, id) -> {
            NhanVien nhanVien = nhanVienList.get(position);
            edtMaNV.setText(nhanVien.getMaNV());
            edtTenNV.setText(nhanVien.getTenNV());
            edtTuoi.setText(String.valueOf(nhanVien.getTuoi()));
        });

        btnThem.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString();
            String tenNV = edtTenNV.getText().toString();
            String tuoiStr = edtTuoi.getText().toString();
            int tuoi = tuoiStr.isEmpty() ? 0 : Integer.parseInt(tuoiStr);
            String maPhongBan = ((PhongBan) spPhongBan.getSelectedItem()).getMaPhongBan();

            if (maNV.isEmpty() || tenNV.isEmpty() || tuoi <= 0) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            long result = myDatabase.addNhanVien(new NhanVien(maNV, maPhongBan, tenNV, tuoi));
            if (result == -1) {
                Toast.makeText(this, "Thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                myDatabase.addNhanVien(new NhanVien(maNV, maPhongBan, tenNV, tuoi));
                loadNhanVienList(maPhongBan);
                edtMaNV.setText("");
                edtTenNV.setText("");
                edtTuoi.setText("");
            }
        });

        btnXoa.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString();
            if (maNV.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mã nhân viên", Toast.LENGTH_SHORT).show();
                return;
            }

            int result = myDatabase.deleteNhanVien(maNV);
            if (result == 0) {
                Toast.makeText(this, "Xóa nhân viên thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                loadNhanVienList(((PhongBan) spPhongBan.getSelectedItem()).getMaPhongBan());
            }
        });

        btnOpenAct.setOnClickListener(v -> {
            PhongBan phongBan = (PhongBan) spPhongBan.getSelectedItem();
            int soNhanVien = myDatabase.getNhanVienCountByPhongBan(phongBan.getMaPhongBan());

            Intent intent = new Intent(getApplicationContext(), Activity_2.class);
            intent.putExtra("tenPhongBan", phongBan.getTenPhongBan());
            intent.putExtra("soNhanVien", soNhanVien);
            startActivity(intent);
        });
    }

    private void loadNhanVienList(String maPhongBan) {
        nhanVienList = myDatabase.getNhanVienByPhongBan(maPhongBan);
        nhanVienAdapter = new NhanVienAdapter(this, nhanVienList, phongBanList.get(spPhongBan.getSelectedItemPosition()).getTenPhongBan() );
        lvDanhSach.setAdapter(nhanVienAdapter);
    }
}