package com.example.mandish_lilac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URL;
import java.util.ArrayList;

/*
작성자: 김강민
최종수정일: 21-05-17
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */
public class FragRecipe extends Fragment {
    private View view;
    private ListView listView;
    private RecipeListViewAdapter adapter;
    ArrayList<Recipe_item> recipe_items;
    public static FragRecipe newInstance() {
        FragRecipe fragRecipe = new FragRecipe();

        return fragRecipe;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_recipe, container,false);
        adapter = new RecipeListViewAdapter(getContext());

        listView =(ListView)view.findViewById(R.id.RecipeListView);
        listView.setAdapter(adapter);
        adapter.addItem("콩비지동그랑땡","두부대신 콩비지를 넣어 만든 동그랑땡 맛도 좋아요!","관리자","http://file.okdab.com/recipe/148299577268400131.jpg");
        adapter.addItem("누드김밥","건강한 재료로 가득담아 말은 누드김밥! 고기가 없어도 맛있어요!!","관리자","http://file.okdab.com/recipe/148299332505800120.jpg");
        adapter.addItem("쪽파 새우강회","새우에 쪽파를 감아 간단하게 만들지만 새우와 쪽파의 달콤한 맛과 새콤한 초고추장의 환상의 콤비! 거기에 건강까지 좋은 영양만점 쪽파 새우강회!!","관리자","http://file.okdab.com/recipe/148299002655300119.jpg");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe_item selectedItem = adapter.getItem(i);

                Toast.makeText(getContext(), "Clicked: " + i +" "+ selectedItem.getRecipe_name() , Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
