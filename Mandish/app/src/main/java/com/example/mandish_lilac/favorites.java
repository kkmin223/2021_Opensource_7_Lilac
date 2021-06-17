/*
작성자: 오기탁
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */
package com.example.mandish_lilac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class favorites extends AppCompatActivity {
    private ImageButton back;//뒤로가기 버튼 구현
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        back = findViewById(R.id.backbtn3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}