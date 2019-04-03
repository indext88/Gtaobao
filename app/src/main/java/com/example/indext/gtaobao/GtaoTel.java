package com.example.indext.gtaobao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class GtaoTel extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText users;
    private EditText password;
    private Button login;
    private CheckBox rememberPass;
    private Fragment myto_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtao_tel);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        pref= PreferenceManager.getDefaultSharedPreferences(this);

        users=findViewById(R.id.edit_user);
        password=findViewById(R.id.edit_password);
        rememberPass=findViewById(R.id.remember_pass);
        login=findViewById(R.id.btn_tel);

        boolean isRememeber=pref.getBoolean("remember_password",false);
        if(isRememeber){
            //将账号和密码都设置到文本框中
            String userss=pref.getString("account","");
            String passwordd=pref.getString("password","");
            users.setText(userss);
            password.setText(passwordd);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userss=users.getText().toString();
                String passwordd=password.getText().toString();
                String um=users.getText().toString();
                String pw=password.getText().toString();
                //如果账户是admin其密码是123456，就认为登录成功
                if("admin".equals(userss)&&"123456".equals(passwordd)){
                    editor=pref.edit();
                    if(rememberPass.isChecked()){
                        //检查复选框是否被选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",userss);
                        editor.putString("password",passwordd);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(GtaoTel.this,Main3Activity.class);
                    startActivity(intent);
                    Toast.makeText(GtaoTel.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(GtaoTel.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
