package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        작성자: 김강민
        최종수정일: 21-05-17
        설명: 뷰 페이저2와 탭 레이아웃을 이용하여 상단 메뉴바 기능 구현
        */
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        ViewPager2Adapter fgAdapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(fgAdapter);
        final List<String> tabElement = Arrays.asList("레시피","커뮤니티","마이페이지");
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(tabElement.get(position));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTextColor(Color.BLACK);
                tab.setCustomView(textView);
            }
        }).attach();


    }
}

