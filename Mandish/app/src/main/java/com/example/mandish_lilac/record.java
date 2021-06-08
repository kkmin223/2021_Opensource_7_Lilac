package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class record extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recipeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Recipe_item> RecipeList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ImageButton back;
    private DatabaseReference userReference;
    private String postWriter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recyclerView = findViewById(R.id.RecipeListView2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecipeList = new ArrayList<Recipe_item>(); //Recipe 객체를 담을 배열
        back = findViewById(R.id.backbtn5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("Recipe/RecipeInfo"); //테이블 연동

        userReference = database.getReference("UserAccount");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user!=null?user.getUid():null;  // 현재 접속중인 user의 uid 가져오기

        userReference.child(uid).child("nic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postWriter = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.orderByChild("writer").startAt("sss").endAt("sss" + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
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


        recipeadapter = new RecipeRecyclerViewAdapter(this, RecipeList);
        recyclerView.setAdapter(recipeadapter); //리사이클러뷰에 어댑터 연결*/
    }

}