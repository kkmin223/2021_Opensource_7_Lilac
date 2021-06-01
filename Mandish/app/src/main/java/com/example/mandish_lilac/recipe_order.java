package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/*
작성자: 김강민
설명: 레시피 설명 xml에 사용자가 선택한 레시피를
보여주는 기능
레시피 코드를 받아와서 해당 코드에 맞는 레시피를 출력
 */


public class recipe_order extends AppCompatActivity {
    private Recipe_item item;
    private Query temp;
    private int code; // 레시피 구분을 위한 레시피 코드
    private FirebaseDatabase database; //데이터 베이스 접근
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference recipeInfoReference;
    private DatabaseReference itemReference;
    private ImageView foodImgView;
    private TextView nameTextView;
    private TextView writerTextView;
    private TextView typenameTextView;
    private TextView foodtypeTextView;
    private TextView difficultyTextView;
    private TextView cooktimeTextView;
    private TextView amountTextView;
    private TextView recTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_order);
        database = FirebaseDatabase.getInstance();
        recipeInfoReference = database.getReference("Recipe/RecipeInfo"); //파이어베이스 데이터베이스 RecipeInfo 연동
        storage = FirebaseStorage.getInstance("gs://mandish-93a90.appspot.com"); //파이어베이스 스토리지 연동
        storageReference = storage.getReference(); // 스토리지 테이블 연동
        Intent intent = getIntent(); // 리사이클러뷰에서 보낸 정보를 받아온다.
        code = intent.getIntExtra("recipe_code",-1);  //레시피 코드를 전 Intent에서 받아온다
        foodImgView = findViewById(R.id.recipe_food);
        nameTextView = findViewById(R.id.recipe_name);
        writerTextView = findViewById(R.id.recipe_writer);
        typenameTextView = findViewById(R.id.type_name);
        foodtypeTextView = findViewById(R.id.food_type);
        difficultyTextView = findViewById(R.id.recipe_dif);
        cooktimeTextView = findViewById(R.id.cooktime);
        amountTextView = findViewById(R.id.amount);
        recTextView = findViewById(R.id.recipe_rec);
        dateTextView = findViewById(R.id.recipe_time);

        recipeInfoReference.addListenerForSingleValueEvent(new ValueEventListener() {
            //레시피 코드를 통해서 데이터베이스에서 음식 정보 가져오기
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot temp : snapshot.getChildren()) {
                    if (temp.getValue(Recipe_item.class).getRecipe_code() == code) {
                        item = temp.getValue(Recipe_item.class);
                        nameTextView.setText(item.getRecipe_name());
                        writerTextView.setText(item.getWriter());
                        typenameTextView.setText("#"+item.getType_name());
                        foodtypeTextView.setText(" #"+item.getFood_type());
                        difficultyTextView.setText("#"+item.getDifficulty());
                        cooktimeTextView.setText(" #"+item.getCooktime());
                        amountTextView.setText(" #"+item.getAmount());
                        recTextView.setText(String.valueOf(item.getRec_cnt()));
                        dateTextView.setText(item.getWrite_date());
                        if(item.getImg_url()==null){
                            //db에 이미지가 없다면 스토리지에서 가져온다
                            storageReference.child("FoodInfo/f_"+item.getRecipe_code()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(recipe_order.this)
                                            .load(uri)
                                            .thumbnail(0.3f)
                                            .dontAnimate()
                                            .error(R.drawable.mandish_logo2)
                                            .into(foodImgView);
                                }
                            });
                        }
                        else {
                            Glide.with(recipe_order.this)
                                    .load(item.getImg_url())
                                    .thumbnail(0.3f)
                                    .dontAnimate()
                                    .error(R.drawable.mandish_logo2)
                                    .fitCenter()
                                    .into(foodImgView);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









    }
}