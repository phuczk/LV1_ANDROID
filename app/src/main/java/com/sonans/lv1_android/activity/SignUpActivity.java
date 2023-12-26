package com.sonans.lv1_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sonans.lv1_android.Dao.UserDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.model.User;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    TextView btnSignIn;
    EditText edName, edUserName, edPass;
    Button btnSignUp;
    UserDao dao;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edName = findViewById(R.id.edName);
        edUserName = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        dao = new UserDao(context);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, SignIn.class);
                startActivity(i);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> ls = new ArrayList<>();

                User tt = new User();
                tt.setHoTen(edName.getText().toString());
                tt.setUserName(edUserName.getText().toString());
                tt.setPassword(edPass.getText().toString());
                if(dao.insert(tt) > 0){
                    Toast.makeText(context, "them thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, SignIn.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(context, "tai khoan da ton tai", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}