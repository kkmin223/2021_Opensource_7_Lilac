package com.example.mandish_lilac;
/*
작성자: 오기탁
설명: 레시피 메뉴에서 레시피 재료틀 출력하기 위한
레시피 기본 정보를 담은 큰래스
*/

import android.net.Uri;

public class Recipe_material {

    private String recipe_name; // 레시피 이름(음식 이름)
    private String recipe_intro; // 레시피 간략소개
    private int type_code; // 레시피 유형 코드
    private String type_name; // 레시피 유형 이름(한식,중식,...)
    private int food_code; // 음식 유형 코드
    private String food_type; //음식 유형 (부침,튀김,...)
    private String amount; // 음식 양을 인분으로 표현
    private String cooktime; // 음식 조리시간
    private String difficulty; //음식 난이도
    private Uri img_url; // 음식 이미지 url
    private String writer; //레시피 작성자 ID
    private int rec_cnt; // 레시피 추천수
    private String  write_date; //작성시간

    private String Material_amount;
    private int Material_code;
    private String Material_name;
    private int Material_order;
    private String Material_type;


    private String getMaterial_amount(){ return Material_amount; }
    private int setMaterial_code(){ return Material_code; }
    private String getMaterial_name(){ return Material_name; }
    private int Material_order(){ return Material_order; }
    private String Material_type(){ return Material_type; }
}
