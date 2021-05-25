package com.example.mandish_lilac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
/*
작성자: 김강민
최종수정일: 21-05-17
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */
public class FragMypage extends Fragment {
    //private View view;
    public static FragMypage newInstance() {
        FragMypage fragMypage = new FragMypage();
        return fragMypage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.frag_mypage, container,false);

        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.frag_mypage,container,false);
        LinearLayout cmdArea = (LinearLayout)rootview.findViewById(R.id.profile);
        cmdArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnFollowers = (Button)rootview.findViewById(R.id.btnFollowers);
        btnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), followers.class);
                startActivity(intent);
            }
        });

        Button btnFavorites = (Button)rootview.findViewById(R.id.btnFavorites);
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), favorites.class);
                startActivity(intent);
            }
        });

        Button btnRecord = (Button)rootview.findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), record.class);
                startActivity(intent);
            }
        });

        Button btnService = (Button)rootview.findViewById(R.id.btnService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), change.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

}

