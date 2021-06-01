package com.example.mandish_lilac;

public class order{
    /*
    작성자: 김강민
    설명: 해당 레시피에 세부 순서를 가지고 있는 클래스
    */
    private int cook_order;
    private String order_description;
    private String order_image;
    private String order_tip;
    private int recipe_code;

    public int getCook_order() {
        return cook_order;
    }

    public void setCook_order(int cook_order) {
        this.cook_order = cook_order;
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = order_description;
    }

    public String getOrder_image() {
        return order_image;
    }

    public void setOrder_image(String order_image) {
        this.order_image = order_image;
    }

    public String getOrder_tip() {
        return order_tip;
    }

    public void setOrder_tip(String order_tip) {
        this.order_tip = order_tip;
    }

    public int getRecipe_code() {
        return recipe_code;
    }

    public void setRecipe_code(int recipe_code) {
        this.recipe_code = recipe_code;
    }
}
