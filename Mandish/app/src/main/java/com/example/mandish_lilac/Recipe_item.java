package com.example.mandish_lilac;
/*
작성자: 김강민
설명: 레시피 메뉴에서 레시피 리스트틀 출력하기 위한
레시피 기본 정보를 담은 큰래스
최종수정일: 21-05-19
*/

import java.net.URL;

public class Recipe_item {

    private int recipe_code; // 레시피 고유 식별번호
    private String recipe_name; // 레시피 이름(음식 이름)
    private String recipe_intro; // 레시피 간략소개
    private int type_code; // 레시피 유형 코드
    private String type_name; // 레시피 유형 이름(한식,중식,...)
    private int food_code; // 음식 유형 코드
    private String food_type; //음식 유형 (부침,튀김,...)
    private int amount; // 음식 양을 인분으로 표현
    private int cooktime; // 음식 조리시간
    private String difficulty; //음식 난이도
    private String img_url; // 음식 이미지 url
    private String writer; //레시피 작성자 ID
    private int rec_cnt; // 레시피 추천수
    private String  write_date; //작성시간

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getRec_cnt() {
        return rec_cnt;
    }

    public void setRec_cnt(int rec_cnt) {
        this.rec_cnt = rec_cnt;
    }

    public int getRecipe_code() {
        return recipe_code;
    }

    public void setRecipe_code(int recipe_code) {
        this.recipe_code = recipe_code;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_intro() {
        return recipe_intro;
    }

    public void setRecipe_intro(String recipe_intro) {
        this.recipe_intro = recipe_intro;
    }

    public int getType_code() {
        return type_code;
    }

    public void setType_code(int type_code) {
        this.type_code = type_code;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getFood_code() {
        return food_code;
    }

    public void setFood_code(int food_code) {
        this.food_code = food_code;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCooktime() {
        return cooktime;
    }

    public void setCooktime(int cooktime) {
        this.cooktime = cooktime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
