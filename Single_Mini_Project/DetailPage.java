package com.example.businesscardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPage extends AppCompatActivity {

    private EditText edtName, edtPhone, edtEmail;

    private Button addInfoBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        addInfoBtn = findViewById(R.id.addInfoBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        addInfoBtn.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();

            if(name.isEmpty() || phone.isEmpty() || email.isEmpty()){
                Toast.makeText(this, "정보를 입력해 주세요", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phoneNum",phone);
                intent.putExtra("email",email);
                setResult(RESULT_OK, intent); // 결과를 설정하고 MainActivity로 반환
                finish(); // DetailPage 종료
//                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });
    }
}