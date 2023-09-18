package com.example.viewbindingtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.viewbindingtest2.databinding.SecondActivityBinding;

public class MainActivity extends AppCompatActivity {

    private SecondActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = SecondActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.edtName.setText("이름");
        binding.txtHello.setText("안녕하세요.");
        binding.ibFace.setOnClickListener(view1 -> {
            binding.ibFace.setImageResource(R.drawable.girls3);
        });


        binding.btnButton.setOnClickListener(view1 -> {
            Toast.makeText(this, "버튼 눌림", Toast.LENGTH_SHORT).show();
        });
    }
}