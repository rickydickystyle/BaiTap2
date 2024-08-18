package com.myname.bt2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_2 extends AppCompatActivity {
    private TextView tvTenPhongBan, tvSoNhanVien;
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);

        tvTenPhongBan = findViewById(R.id.tvTenPhongBan);
        tvSoNhanVien = findViewById(R.id.tvSoNhanVien);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhongBan = intent.getStringExtra("tenPhongBan");
        int soNhanVien = intent.getIntExtra("soNhanVien", 0);

        // Hiển thị dữ liệu
        tvTenPhongBan.setText("Tên phòng ban: " + tenPhongBan);
        tvSoNhanVien.setText("Số nhân viên: " + soNhanVien);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}