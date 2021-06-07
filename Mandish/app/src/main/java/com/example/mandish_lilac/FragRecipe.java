package com.example.mandish_lilac;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
최종수정일: 21-05-17
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */
public class FragRecipe extends Fragment{
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
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
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("FragRecipe", String.valueOf(error.toException()));
            }
        });
        adapter = new RecipeRecyclerViewAdapter(RecipeList);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        return view;
    }

    private View firebaseUserSearch(String searchText) {
        Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_LONG).show();
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
            adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // 디비를 가져오던중 에러 발생 시
            Log.e("FragRecipe", String.valueOf(error.toException()));
        }
    });
        adapter = new RecipeRecyclerViewAdapter(RecipeList);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        return view;
    }




    /*@Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeRecyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }*/
}
