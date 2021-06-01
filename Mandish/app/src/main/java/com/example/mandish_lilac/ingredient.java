package com.example.mandish_lilac;

public class ingredient {
    /*
    작성자: 김강민
    설명: 레시피에 필요한 재료 클래스
    */
    private String Material_amount; // 재료양
    private int Material_code; //재료 코드
    private String Material_name; // 재료 이름
    private int Material_order; // 재료 순서
    private String Material_type; // 재료 유형
    private int recipe_code; // 레시피 코드

    public String getMaterial_amount() {
        return Material_amount;
    }

    public void setMaterial_amount(String material_amount) {
        Material_amount = material_amount;
    }

    public int getMaterial_code() {
        return Material_code;
    }

    public void setMaterial_code(int material_code) {
        Material_code = material_code;
    }

    public String getMaterial_name() {
        return Material_name;
    }

    public void setMaterial_name(String material_name) {
        Material_name = material_name;
    }

    public int getMaterial_order() {
        return Material_order;
    }

    public void setMaterial_order(int material_order) {
        Material_order = material_order;
    }

    public String getMaterial_type() {
        return Material_type;
    }

    public void setMaterial_type(String material_type) {
        Material_type = material_type;
    }

    public int getRecipe_code() {
        return recipe_code;
    }

    public void setRecipe_code(int recipe_code) {
        this.recipe_code = recipe_code;
    }
}
