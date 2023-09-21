package com.example.diarynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

//        데이터베이스--------------------------------------------------
        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(DetailPage.this, "userContents.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);
//        -----------------------------------------------------------------------


        cancelBtn =findViewById(R.id.cancelBtn);
        detailTitle=findViewById(R.id.detailTitle);
        detailContents = findViewById(R.id.detailContents);
        submitBtn = findViewById(R.id.submitBtn);

//        날짜지정------------------------------------------------------------
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = dateFormat.format(currentDate);
        String date = "";

        detailDate = findViewById(R.id.detailDate);

        Intent intent = getIntent();
        String Date = intent.getStringExtra("Date");

        detailDate.setText(Date);

//        if(Year != 0 || Month != 0 || Day != 0){
//            detailDate.setText(Date);
//
//        }else{
//            detailDate.setText(formattedDate);
//        }
//-------------------------------------------------------------------------------------

        // 사용자가 선택한 날짜
        String selectedDate = detailDate.getText().toString();
        // 데이터베이스에서 해당 날짜의 글을 검색
        String[] columns = {"title", "contents"};
        String selection = "date = ?";
        String[] selectionArgs = {selectedDate};
        Cursor cursor = db.query("mytable", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // 해당 날짜에 작성한 글이 있는 경우
            @SuppressLint("Range") String dbTitle = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String dbContents = cursor.getString(cursor.getColumnIndex("contents"));

            // dbTitle과 dbContents를 화면에 표시
            detailTitle.setText(dbTitle);
            detailContents.setText(dbContents);
        } else {
            detailTitle.setText("");
            detailContents.setText("");
        }


//        Intent intent2 = getIntent();
//        String getTitle = intent2.getStringExtra("mainTitle");
//        String getContents = intent2.getStringExtra("mainContents");
//        detailTitle.setText(getTitle);
//        detailContents.setText(getContents);


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
                //디비에 insert 하기
                ContentValues values = new ContentValues();
                values.put("title",detailTitle.getText().toString());
                values.put("contents",detailContents.getText().toString());
                values.put("date",detailDate.getText().toString());
                db.insert("mytable",null,values);


                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.putExtra("date",formattedDate);
                startActivity(intent1);
            }

        });
    }
    private void CancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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