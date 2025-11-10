package com.xxs.order.activity.user;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import com.xxs.order.activity.man.RegisterManActivity;
import com.xxs.order.dao.AdminDao;
import com.xxs.order.util.FileImgUtil;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> getContentLauncher;

    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = findViewById(R.id.register_user_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView imgText = findViewById(R.id.register_user_tx);



        getContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!=null){
                    imgText.setImageURI(result);
                    uri = result;
                }else{
                    Toast.makeText(RegisterUserActivity.this, "请选择图片", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Drawable defaultDrawable = ContextCompat.getDrawable(this, R.drawable.upimg);

        EditText idText = findViewById(R.id.register_user_account);
        EditText pwdText = findViewById(R.id.register_user_pwd);
        EditText nameText = findViewById(R.id.register_user_name);

        String sex = "女";
        RadioButton man = findViewById(R.id.register_user_nan);
        man.setChecked(true);
        if(man.isChecked()){
            sex = "男";
        }

        EditText addressText = findViewById(R.id.register_user_address);
        EditText phoneText = findViewById(R.id.register_user_phone);

        Button reg = findViewById(R.id.register_user_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pwd = pwdText.getText().toString();
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();
                String phone = phoneText.getText().toString();
                Drawable drawable = imgText.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();

                if (bitmap.sameAs(bitmapDef)) {
                    Toast.makeText(RegisterUserActivity.this, "请点击图片进行添加头像", Toast.LENGTH_SHORT).show();
                } else if(id.isEmpty()){
                    Toast.makeText(RegisterUserActivity.this, "请输入用户账号", Toast.LENGTH_SHORT).show();
                } else if(pwd.isEmpty()){
                    Toast.makeText(RegisterUserActivity.this, "请输入用户密码", Toast.LENGTH_SHORT).show();
                } else if(address.isEmpty()){
                    Toast.makeText(RegisterUserActivity.this, "请输入用户住址", Toast.LENGTH_SHORT).show();
                } else if(phone.isEmpty()){
                    Toast.makeText(RegisterUserActivity.this, "请输入用户手机", Toast.LENGTH_SHORT).show();
                } else if(name.isEmpty()){
                    Toast.makeText(RegisterUserActivity.this, "请输入用户名称", Toast.LENGTH_SHORT).show();
                }else {
                    String path = FileImgUtil.getImageName();
                    FileImgUtil.saveImageBitmapToFileImg(uri,RegisterUserActivity.this,path);
                    int i = AdminDao.saveBusinessUser(id, pwd, name, address, phone, path);
                    if(i == 1){
                        Toast.makeText(RegisterUserActivity.this, "注册用户成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterUserActivity.this, "注册用户失败", Toast.LENGTH_SHORT).show();
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
