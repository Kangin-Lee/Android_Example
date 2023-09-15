package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView Text1;
    private CheckBox ChkAgree;
    private RadioGroup rGroup1;
    private RadioButton rdoDog, rdoCat, rdoRabbit;
    private Button btnOk;
    private ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        //리소스 초기화==========================================================================
        Text1 = findViewById(R.id.Text1);
        ChkAgree = findViewById(R.id.ChkAgree);
        rGroup1 = findViewById(R.id.Rgroup1);
        rdoDog = findViewById(R.id.RdoDog);
        rdoCat = findViewById(R.id.RdoCat);
        rdoRabbit = findViewById(R.id.RdoRabbit);
        btnOk = findViewById(R.id.BtnOk);
        imgPet = findViewById(R.id.ImgPet);
        ///////////////////////////////////////////////////////////////////////////////////////////

        //체크박스 이벤트 처리=--------------------------------------------------------------
        ChkAgree.setOnCheckedChangeListener((compoundButton, b) -> {
            if(ChkAgree.isChecked() == true){
                rGroup1.setVisibility(View.VISIBLE);
                btnOk.setVisibility(View.VISIBLE);
                imgPet.setVisibility(View.VISIBLE);
            }else{
                rGroup1.setVisibility(View.INVISIBLE);
                btnOk.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////버튼이벤트 처리////////////////////////////////
        btnOk.setOnClickListener(view -> {

            if(rGroup1.getCheckedRadioButtonId() == R.id.RdoDog){
                imgPet.setImageResource(R.drawable.dog);
            }else if(rGroup1.getCheckedRadioButtonId() == R.id.RdoCat){
                imgPet.setImageResource(R.drawable.cat);
            }else if(rGroup1.getCheckedRadioButtonId() == R.id.RdoRabbit){
                imgPet.setImageResource(R.drawable.rabbit);
            }else{
                Toast.makeText(this, "원하시는 그림이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        ///////////////////////////////////////////////////////////////////
    }
}