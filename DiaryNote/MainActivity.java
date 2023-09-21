package com.example.diarynote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private LinearLayout diaryLayout;
    private TextView txtTitle, txtContents;
    private CalendarView calendarView;

    private String selectDate = "";

    private DBHelper dbHelper; // DBHelper 클래스를 사용하기 위한 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryLayout = findViewById(R.id.diaryLayout);
        txtTitle = findViewById(R.id.txtTitle);
        txtContents = findViewById(R.id.txtContents);
        calendarView = findViewById(R.id.calendarView);

        String title = txtTitle.getText().toString();
        String contents = txtContents.getText().toString();

        diaryLayout.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), DetailPage.class);
            startActivity(intent);
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dbHelper = new DBHelper(getApplicationContext(), "userContents.db", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                // 선택한 날짜를 selectDate에 저장
                selectDate = (year + "." + (month + 1) + "." + dayOfMonth).toString();

                // selectDate에 해당하는 데이터를 데이터베이스에서 가져오기
                String[] columns = {"title", "contents", "date"};
                String selection = "date = ?";
                String[] selectionArgs = {selectDate};
                Cursor cursor = db.query("mytable", columns, selection, selectionArgs, null, null, null);

                if (cursor.moveToFirst()) {
                    // 해당 날짜에 작성한 글이 있는 경우
                    @SuppressLint("Range") String dbTitle = cursor.getString(cursor.getColumnIndex("title"));
                    @SuppressLint("Range") String dbContents = cursor.getString(cursor.getColumnIndex("contents"));

                    // 불러온 글의 title과 contents를 화면에 표시
                    txtTitle.setText(dbTitle);
                    txtContents.setText(dbContents);
                } else {
                    txtTitle.setText("");
                    txtContents.setText("");
                }

//                Intent intent = new Intent(getApplicationContext(), DetailPage.class);
//                intent.putExtra("Year", year);
//                intent.putExtra("Month", month);
//                intent.putExtra("Day", dayOfMonth);
//                selectDate = (year+"."+(month+1)+"."+dayOfMonth).toString();
//                startActivity(intent);
            }
        });


//        글작성 페이지에서 쓴 제목, 내용 받기--------------------------------------------------------
//        Intent intent = getIntent();
//        String detailTitle = intent.getStringExtra("title");
//        String detailContents = intent.getStringExtra("contents");
//        txtTitle.setText(detailTitle);
//        txtContents.setText(detailContents);

//        데이터베이스에 있는 값 출력하기------------------------------------------------------------if else문 써서 날짜랑 같으면 꺼내라 이런식이면 될 듯?
        // 데이터베이스 생성 또는 열기

//        String[] columns = {"title", "contents", "date"};
//        Cursor cursor = db.query("mytable", columns, null, null, null, null, null);
//
//
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range") String dbtitle = cursor.getString(cursor.getColumnIndex("title"));
//            @SuppressLint("Range") String dbcontents = cursor.getString(cursor.getColumnIndex("contents"));
//            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
//
//            Intent intent = getIntent();
//            String detailDate = intent.getStringExtra("date");
//            // 가져온 데이터를 사용하여 원하는 처리를 수행합니다.
//            txtTitle.setText(detailDate);
//            txtContents.setText(dbcontents);
//        }


//        // 결과 출력
//        String sql = "select * from mytable where date = '" + selectDate + "';";
//        Cursor c = db.rawQuery(sql, null);
//        while(c.moveToNext()){
//            txtTitle.setText(c.getColumnIndex("title"));
//            txtContents.setText(c.getColumnIndex("contents"));
//        }

    }

    private void dateChange() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dbHelper = new DBHelper(getApplicationContext(), "userContents.db", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                // 선택한 날짜를 selectDate에 저장
                selectDate = (year + "." + (month + 1) + "." + dayOfMonth).toString();


                Intent intent = new Intent(getApplicationContext(), DetailPage.class);
                intent.putExtra("Date",selectDate);
                startActivity(intent);


                // selectDate에 해당하는 데이터를 데이터베이스에서 가져오기
                String[] columns = {"title", "contents", "date"};
                String selection = "date = ?";
                String[] selectionArgs = {selectDate};
                Cursor cursor = db.query("mytable", columns, selection, selectionArgs, null, null, null);

                if (cursor.moveToFirst()) {
                    // 해당 날짜에 작성한 글이 있는 경우
                    @SuppressLint("Range") String dbTitle = cursor.getString(cursor.getColumnIndex("title"));
                    @SuppressLint("Range") String dbContents = cursor.getString(cursor.getColumnIndex("contents"));

                    // 불러온 글의 title과 contents를 화면에 표시
                    txtTitle.setText(dbTitle);
                    txtContents.setText(dbContents);
                } else {
                    txtTitle.setText("");
                    txtContents.setText("");
                }

//                Intent intent = new Intent(getApplicationContext(), DetailPage.class);
//                intent.putExtra("Year", year);
//                intent.putExtra("Month", month);
//                intent.putExtra("Day", dayOfMonth);
//                selectDate = (year+"."+(month+1)+"."+dayOfMonth).toString();
//                startActivity(intent);
            }
        });
    }
}