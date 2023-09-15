package com.example.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] fruits = new String[] { "사과", "딸기", "배"};
        final boolean[] checkArr = new boolean[] {true, false, false};

        Button btnDialogOpen = findViewById(R.id.btnDialogOpen);

        btnDialogOpen.setOnClickListener(view -> {
            //--------------다이얼로그 만들기------------------//
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("제목 입니다.");
            //dlg.setMessage("이곳에 메시지가 들어갑니다.");
            dlg.setIcon(R.mipmap.ic_launcher);
//            dlg.setItems(fruits, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    btnDialogOpen.setText(fruits[i]);
//                }
//            });

//            dlg.setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    btnDialogOpen.setText(fruits[i]);
//                }
//            });

            dlg.setMultiChoiceItems(fruits, checkArr, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    btnDialogOpen.setText(fruits[i]);
                }
            });

            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "확인을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "취소 버튼 누름", Toast.LENGTH_SHORT).show();
                }
            });
            dlg.setNeutralButton("뒤로",null);
            dlg.show();
        });

    }
}