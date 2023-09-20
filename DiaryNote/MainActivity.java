package com.example.diarynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout diaryLayout;
    private TextView txtTitle, txtContents;
    private CalendarView calendarView;

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

            if(title.isEmpty() || contents.isEmpty()){
                dateChange();
                Intent intent1 = new Intent(getApplicationContext(), DetailPage.class);
                startActivity(intent1);
            }else{
                dateChange();
                Intent intent = new Intent(getApplicationContext(),DetailPage.class);
                intent.putExtra("mainTitle",txtTitle.getText().toString());
                intent.putExtra("mainContents",txtContents.getText().toString());
                startActivity(intent);
            }
        });

//        글작성 페이지에서 쓴 제목, 내용 받기--------------------------------------------------------
        Intent intent = getIntent();
        String detailTitle = intent.getStringExtra("title");
        String detailContents = intent.getStringExtra("contents");
        txtTitle.setText(detailTitle);
        txtContents.setText(detailContents);
    }

    private void dateChange(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(getApplicationContext(), DetailPage.class);
                intent.putExtra("Year", year);
                intent.putExtra("Month", month);
                intent.putExtra("Day", dayOfMonth);
                startActivity(intent);
            }
        });
    }
}