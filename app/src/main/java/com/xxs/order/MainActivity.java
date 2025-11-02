package com.xxs.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xxs.order.activity.man.RegisterManActivity;
import com.xxs.order.activity.user.RegisterUserActivity;
import com.xxs.order.db.DBUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBUtil dbUtil = new DBUtil(this);
        DBUtil.con = dbUtil.getWritableDatabase();

        RadioButton sjRadio = findViewById(R.id.login_sj);
        sjRadio.setChecked(true);
        Button zcsj = findViewById(R.id.login_zhuceshangjia);
        zcsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterManActivity.class);
                startActivity(intent);
            }
        });
        Button zcyh = findViewById(R.id.login_zheceyonghu);
        zcyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }
}