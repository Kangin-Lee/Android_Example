package com.example.diarynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(Register.this, "userContents.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        etUsername = findViewById(R.id.et_pass);
        etPassword = findViewById(R.id.et_name);
        registerButton = findViewById(R.id.register_btn);

        registerButton.setOnClickListener(view -> {
            // 사용자가 입력한 아이디와 패스워드 가져오기
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "정보를 정확히 입력해 주세요.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();


                //디비에 정보 저장=========================================================
                ContentValues values = new ContentValues();
                values.put("id",username);
                values.put("pwd",password);
                db.insert("login",null,values);

                //로그인 화면 돌아가기========================================================
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}