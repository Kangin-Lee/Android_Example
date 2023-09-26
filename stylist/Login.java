package com.example.stylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button loginSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginSubmit = findViewById(R.id.loginSubmit);

        loginSubmit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main.class);
            startActivity(intent);
        });
    }
}