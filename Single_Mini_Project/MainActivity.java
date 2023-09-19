package com.example.businesscardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addBtn;

    private ArrayList<Person> itemList = new ArrayList<>();
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);

//        List<String> itemList = new ArrayList<String>();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String data = (String) adapterView.getItemAtPosition(i);
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        });

//        화면 전환-----------------------------------------------------------------------
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DetailPage.class);
            startActivityForResult(intent, 1); // 새로운 정보를 받기 위해 startActivityForResult 사용
        });

//        값 받기----------------------------------------------------------------------------
        Intent intent1 = getIntent();
//        String name = intent1.getStringExtra("name");
//        String phone = intent1.getStringExtra("phoneNum");
//        String email = intent1.getStringExtra("email");


        adapter = new CustomAdapter(this, R.layout.list_view,itemList);

        listView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phoneNum");
            String email = data.getStringExtra("email");

            Person md = new Person(R.drawable.ic_launcher_foreground, name, phone, email);
            itemList.add(md);
            adapter.notifyDataSetChanged(); // 어댑터에 데이터가 변경되었음을 알림
        }
    }

}