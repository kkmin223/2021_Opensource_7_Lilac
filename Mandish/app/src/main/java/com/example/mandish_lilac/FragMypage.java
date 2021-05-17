package com.example.mandish_lilac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/*
작성자: 김강민
최종수정일: 21-05-17
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */
public class FragMypage extends Fragment {
    private View view;

    public static FragMypage newInstance() {
        FragMypage fragMypage = new FragMypage();
        return fragMypage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage, container,false);

        return view;
    }
}
