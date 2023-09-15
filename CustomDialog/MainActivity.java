package com.example.customdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvName,tvEmail;
    private Button button1;

    private EditText dlgEdt1, dlgEdt2;

    private TextView toastText1;

    private View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvEmail =findViewById(R.id.tvEmail);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(view -> {
            dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);

            AlertDialog.Builder dlg = new AlertDialog.Builder (MainActivity.this);
            dlg.setTitle("사용자 정보 입력");
            dlg.setIcon(R.drawable.ic_menu_allfriends);
            dlg.setView(dialogView);
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dlgEdt1 = dialogView.findViewById(R.id.dlgEdt1);
                    dlgEdt2 = dialogView.findViewById(R.id.dlgEdt2);

                    tvName.setText(dlgEdt1.getText().toString());
                    tvEmail.setText(dlgEdt2.getText().toString());
                }
            });
            dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast toast = new Toast(MainActivity.this);
                    toastView = View.inflate(MainActivity.this, R.layout.toast1, null);

                    toastText1 = toastView.findViewById(R.id.toastText1);
                    toastText1.setText("취소했습니다.");
                    toast.setView(toastView);
                    toast.show();
                }
            });
            dlg.show();
        });
    }
}