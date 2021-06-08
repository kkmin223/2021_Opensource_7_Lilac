package com.example.mandish_lilac;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

/*
작성자: 김강민
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
파이어베이스와 연동을 통해서 레시피 정보를 가져온 후 
리사이클러 뷰를 사용해서 레시피 리스트 출력
 */
public class FragRecipe extends Fragment{
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recipeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Recipe_item> RecipeList, filteredList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    private SearchView searchView;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private Animation writebtn_open, writebtn_clsoe;
    private FloatingActionButton writebtn, writerecipebtn, writepostbtn; //글쓰기 버튼
    private Boolean isWritebtnOpen = false;
    private TextView btntext1,btntext2; //글쓰기 버튼 Text


    public static FragRecipe newInstance() {
        FragRecipe fragRecipe = new FragRecipe();


        return fragRecipe;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.frag_recipe, container,false);
        Context context = view.getContext();
        recyclerView = (RecyclerView)view.findViewById(R.id.RecipeListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        RecipeList = new ArrayList<Recipe_item>(); //Recipe 객체를 담을 배열
        writebtn_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.writebtn_open);
        writebtn_clsoe = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.writebtn_close);


        writebtn = (FloatingActionButton)view.findViewById(R.id.writebtn);
        writerecipebtn = (FloatingActionButton)view.findViewById(R.id.writerecipebtn);
        writepostbtn = (FloatingActionButton)view.findViewById(R.id.writepostbtn);
        btntext1 = (TextView)view.findViewById(R.id.btntext1);
        btntext2 = (TextView)view.findViewById(R.id.btntext2);
        writebtn.setOnClickListener(this::writebtn_onClick);
        writerecipebtn.setOnClickListener(this::writebtn_onClick);
        writepostbtn.setOnClickListener(this::writebtn_onClick);

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("Recipe/RecipeInfo"); //테이블 연동




        mSearchField = (EditText)view.findViewById(R.id.search_field);
        mSearchBtn = (ImageButton)view.findViewById(R.id.search_btn);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는곳
                RecipeList.clear(); // 기존 배열리스트 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //반복문으로 데이터 리스트 추출
                    Recipe_item recipe_item = snapshot.getValue(Recipe_item.class); //만들어 두었던 레시피 아이템 객체에 담는다
                    RecipeList.add(recipe_item); // 담은 데이터들을 배열리스트에 넣는다.

                }
                recipeadapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("FragRecipe", String.valueOf(error.toException()));
            }
        });
        recipeadapter = new RecipeRecyclerViewAdapter(context,RecipeList);
        recipeadapter.notifyDataSetChanged();
        recyclerView.setAdapter(recipeadapter); //리사이클러뷰에 어댑터 연결


        return view;
    }

    private View firebaseUserSearch(String searchText) {
        Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_LONG).show();
        Context context = view.getContext();
        databaseReference.orderByChild("recipe_name").startAt(searchText).endAt(searchText + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //파이어베이스 데이터베이스의 데이터를 받아오는곳
            RecipeList.clear(); // 기존 배열리스트 존재하지않게 초기화
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                //반복문으로 데이터 리스트 추출
                Recipe_item recipe_item = snapshot.getValue(Recipe_item.class); //만들어 두었던 레시피 아이템 객체에 담는다
                RecipeList.add(recipe_item); // 담은 데이터들을 배열리스트에 넣는다.

            }
            recipeadapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // 디비를 가져오던중 에러 발생 시
            Log.e("FragRecipe", String.valueOf(error.toException()));
        }

    });
        recipeadapter = new RecipeRecyclerViewAdapter(context,RecipeList);
        recyclerView.setAdapter(recipeadapter); //리사이클러뷰에 어댑터 연결

        return view;
    }

    public void writebtn_onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.writebtn:
                btnanim();
                Toast.makeText(getContext(),"writeBtn",Toast.LENGTH_SHORT).show();
                break;
            case R.id.writerecipebtn:
                btnanim();
                Intent intent = new Intent(getActivity(),writerecipe.class);
                startActivity(intent);
                recipeadapter.notifyDataSetChanged();
                break;
            case R.id.writepostbtn:
                btnanim();
                Toast.makeText(getContext(),"writepostbtn",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void btnanim(){
        if(isWritebtnOpen){
            writerecipebtn.startAnimation(writebtn_clsoe);
            writepostbtn.startAnimation(writebtn_clsoe);
            writebtn.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            writebtn.setImageResource(android.R.drawable.ic_input_add);
            writerecipebtn.setClickable(false);
            writepostbtn.setClickable(false);
            btntext1.setVisibility(View.INVISIBLE);
            btntext2.setVisibility(View.INVISIBLE);
            isWritebtnOpen = false;
        }
        else {
            writerecipebtn.startAnimation(writebtn_open);
            writepostbtn.startAnimation(writebtn_open);

            writebtn.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            writerecipebtn.setClickable(true);
            writepostbtn.setClickable(true);
            btntext1.setVisibility(View.VISIBLE);
            btntext2.setVisibility(View.VISIBLE);
            isWritebtnOpen = true;
        }
    }

}
