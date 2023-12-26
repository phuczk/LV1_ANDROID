package com.sonans.lv1_android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sonans.lv1_android.Dao.UserDao;
import com.sonans.lv1_android.R;

public class SignIn extends AppCompatActivity {

    Button btnLogin;
    TextView btnSignUp;
    EditText edName, edPass;
    UserDao dao;
    CheckBox chkPass;
    String strUser, strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edName = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        chkPass = findViewById(R.id.chkRememberPassword);
        dao = new UserDao(this);

        SharedPreferences sp = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = sp.getString("USERNAME", "");
        String password = sp.getString("PASSWORD", "");
        Boolean check = sp.getBoolean("CHECKBOX", false);

        edName.setText(user);
        edPass.setText(password);
        chkPass.setChecked(check);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void rememberPassword(String u, String p, boolean c){
        SharedPreferences sp = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if(!c){
            edit.clear();
        }else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("CHECKBOX", c);
        }
        edit.commit();
    }
    public void checkLogin(){
        strUser = edName.getText().toString();
        strPass = edPass.getText().toString();
        if(strUser.isEmpty()){
            edName.setError("chua nhap thong tin");
        }else if(strPass.isEmpty()){
            edPass.setError("chua nhap thong tin");
        }else if(strUser.isEmpty() && strPass.isEmpty()){
            edName.setError("chua nhap thong tin");
            edPass.setError("chua nhap thong tin");
        }else {
            if(dao.checkLogin(strUser, strPass )>0){
                Toast.makeText(this, "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                rememberPassword(strUser, strPass, chkPass.isChecked());
                Intent i = new Intent(SignIn.this, MainActivity.class);
                // put du lieu
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(this, "dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}