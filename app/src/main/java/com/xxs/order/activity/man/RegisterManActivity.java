package com.xxs.order.activity.man;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xxs.order.R;
import com.xxs.order.dao.AdminDao;
import com.xxs.order.util.FileImgUtil;

import java.util.UUID;

public class RegisterManActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> getContentLauncher;

    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_man);
        Toolbar toolbar = findViewById(R.id.register_man_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView imgText = findViewById(R.id.register_man_tx);



        getContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!=null){
                    imgText.setImageURI(result);
                    uri = result;
                }else{
                    Toast.makeText(RegisterManActivity.this, "请选择图片", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Drawable defaultDrawable = ContextCompat.getDrawable(this, R.drawable.upimg);

        EditText idText = findViewById(R.id.register_man_id);
        EditText pwdText = findViewById(R.id.register_man_pwd);
        EditText nameText = findViewById(R.id.register_man_name);
        EditText desText = findViewById(R.id.register_man_des);
        EditText typeText = findViewById(R.id.register_man_type);

        Button reg = findViewById(R.id.register_man_zcsj);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pwd = pwdText.getText().toString();
                String name = nameText.getText().toString();
                String des = desText.getText().toString();
                String type = typeText.getText().toString();
                Drawable drawable = imgText.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();

                if (bitmap.sameAs(bitmapDef)) {
                    Toast.makeText(RegisterManActivity.this, "请点击图片进行添加头像", Toast.LENGTH_SHORT).show();
                } else if(id.isEmpty()){
                    Toast.makeText(RegisterManActivity.this, "请输入店铺账号", Toast.LENGTH_SHORT).show();
                } else if(pwd.isEmpty()){
                    Toast.makeText(RegisterManActivity.this, "请输入店铺密码", Toast.LENGTH_SHORT).show();
                } else if(des.isEmpty()){
                    Toast.makeText(RegisterManActivity.this, "请输入店铺描述", Toast.LENGTH_SHORT).show();
                } else if(type.isEmpty()){
                    Toast.makeText(RegisterManActivity.this, "请输入店铺类型", Toast.LENGTH_SHORT).show();
                } else if(name.isEmpty()){
                    Toast.makeText(RegisterManActivity.this, "请输入店铺名称", Toast.LENGTH_SHORT).show();
                }else {
                    String path = FileImgUtil.getImageName();
                    FileImgUtil.saveImageBitmapToFileImg(uri,RegisterManActivity.this,path);
                    int i = AdminDao.saveBusinessUser(id, pwd, name, des, type, path);
                    if(i == 1){
                        Toast.makeText(RegisterManActivity.this, "注册商家成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterManActivity.this, "注册商家失败", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        imgText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openGallery(v);
            }
        });
    }

    private void openGallery(View view){
        getContentLauncher.launch("image/*");
    }
}