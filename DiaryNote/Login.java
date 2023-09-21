package com.example.diarynote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText etLoginUsername;
    private EditText etLoginPassword;
    private Button loginButton;
    private Button  register_btn;

    private DBHelper dbHelper; // DBHelper 클래스를 사용하기 위한 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUsername = findViewById(R.id.et_id);
        etLoginPassword = findViewById(R.id.editTextTextPassword2);
        loginButton = findViewById(R.id.btn_login);
        register_btn = findViewById(R.id.btn_register);

//로그인 버튼 누르기
        loginButton.setOnClickListener(view -> {
            String userId = etLoginUsername.getText().toString();
            String userPwd = etLoginPassword.getText().toString(); // 올바른 변수에서 값을 가져오도록 수정

            // DBHelper 객체 초기화
            dbHelper = new DBHelper(this, "userContents.db", null, 1);

            // getUserInfo 메서드를 사용하여 id와 pwd 컬럼을 SELECT
            Cursor cursor = dbHelper.getUserInfo(userId);

            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String storedPwd = cursor.getString(cursor.getColumnIndex("pwd"));

                if (storedPwd.equals(userPwd)) {
                    Toast.makeText(this, userId + "님 반갑습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "아이디, 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        회원가입 가기----------------------------------------------------------------------
        register_btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });
    }
}