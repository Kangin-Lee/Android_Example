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
            intent.putExtra("selectDate",selectDate);
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
            }
        });
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
            }
        });
    }
}