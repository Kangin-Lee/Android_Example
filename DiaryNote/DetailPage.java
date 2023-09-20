package com.example.diarynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailPage extends AppCompatActivity {

    private TextView detailDate;
    private Button cancelBtn, submitBtn;

    private EditText detailTitle, detailContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        cancelBtn =findViewById(R.id.cancelBtn);
        detailTitle=findViewById(R.id.detailTitle);
        detailContents = findViewById(R.id.detailContents);
        submitBtn = findViewById(R.id.submitBtn);

//        날짜지정------------------------------------------------------------
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = dateFormat.format(currentDate);

        detailDate = findViewById(R.id.detailDate);

        Intent intent = getIntent();
        int Year = intent.getIntExtra("Year",0); // 기본값은 0 또는 원하는 값으로 설정
        int Month = intent.getIntExtra("Month", 0);
        int Day = intent.getIntExtra("Day", 0);

        if(Year != 0 || Month != 0 || Day != 0){
            detailDate.setText(Year+"."+(Month+1)+"."+Day);
        }else{
            detailDate.setText(formattedDate);
        }
//-------------------------------------------------------------------------------------

        Intent intent2 = getIntent();
        String getTitle = intent2.getStringExtra("mainTitle");
        String getContents = intent2.getStringExtra("mainContents");
        detailTitle.setText(getTitle);
        detailContents.setText(getContents);


//        취소 버튼 이벤트-------------------------------------------------------
        cancelBtn.setOnClickListener(view -> {
            CancelConfirmationDialog();

        });

//        확인 버튼 이벤트--------------------------------------------------------

        submitBtn.setOnClickListener(view -> {
            String detailTitle1 = detailTitle.getText().toString();
            String detailContents1 = detailContents.getText().toString();
            if(detailTitle1.isEmpty() && detailContents1.isEmpty()){
                Toast.makeText(this, "정확히 입력해 주세요.", Toast.LENGTH_SHORT).show();
            }
            else if(detailTitle1.isEmpty()) {
                Toast.makeText(this, "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            } else if (detailContents1.isEmpty()) {
                Toast.makeText(this, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.putExtra("title", detailTitle.getText().toString());
                intent1.putExtra("contents", detailContents.getText().toString());
                startActivity(intent1);
            }

        });
    }
    private void CancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("확인");
        builder.setMessage("취소하시면 작성 중인 내용은 저장되지않습니다. 그래도 취소하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}