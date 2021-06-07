package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
* 작성자 김강민
* 내용 레시피 추가 Layout
* 사용자가 입력한 정보를 데이터베이스에 저장
* */
public class writerecipe extends AppCompatActivity {
    private EditText erecipename;
    private EditText edifficulty;
    private EditText efood;
    private EditText efoodtype;
    private EditText eamount;
    private EditText etime;
    private EditText eintro;
    private EditText eingname;
    private EditText eingamount;
    private EditText eingtype;
    private EditText edescription;
    private EditText etip;
    private Button addrecipebtn; //레시피 추가 완료버튼
    private Button addingbtn; // 레시피 재료 추가 버튼
    private Button delingbtn; // 레시피 재료 삭제 버튼
    private Button addorderbtn; // 레시피 순서 추가 버튼
    private Button delorderbtn; // 레시피 순서 삭제 버튼
    private ImageButton backbtn; //뒤로 가기 버튼
    private FirebaseDatabase database;
    private DatabaseReference recipeInfoReference;  //레시피 정보 데이터 베이스 테이블
    private DatabaseReference orderReference;       //요리 순서 데이터 베이스 테이블
    private DatabaseReference ingredientReference;  //요리 재료 데이터 베이스 테이블
    private DatabaseReference userReference;  //유저 정보 데이터 베이스 테이블
    private Recipe_item inputInfo;
    private RecyclerView ingredientrecyclerView;
    private RecyclerView.Adapter ingredientadapter;
    private RecyclerView.LayoutManager ingredientlayoutManager;
    private RecyclerView orderrecyclerView;
    private RecyclerView.Adapter orderadapter;
    private RecyclerView.LayoutManager orderlayoutManager;
    private ArrayList<order> inputorders;
    private ArrayList<ingredient> inputingredients;
    private ingredientRecyclerViewAdapter inputingadapter;
    private orderRecyclerViewAdapter inputorderadapter;
    private long mNow;
    private Date mDate;
    private int ingorder;
    private int cookorder;

    public boolean isInfofull(){
        if(erecipename.length()!=0&& eamount.length()!= 0 && edifficulty.length() != 0 && efood.length()!=0
                && efoodtype.length()!=0 && etime.length()!=0 && eintro.length() !=0){
            return true;
        }
        else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writerecipe);
        erecipename = findViewById(R.id.erecipename);
        edifficulty = findViewById(R.id.edifficulty);
        efood = findViewById(R.id.efood);
        efoodtype = findViewById(R.id.efoodtype);
        eamount = findViewById(R.id.eamount);
        etime = findViewById(R.id.etime);
        eintro = findViewById(R.id.eintro);
        eingname = findViewById(R.id.eingname);
        eingamount = findViewById(R.id.eingamount);
        eingtype = findViewById(R.id.eingtype);
        edescription = findViewById(R.id.edescription);
        etip = findViewById(R.id.etip);
        addrecipebtn = findViewById(R.id.addrecipebtn);
        addingbtn = findViewById(R.id.addingbtn);
        delingbtn = findViewById(R.id.delingbtn);
        addorderbtn = findViewById(R.id.addorderbtn);
        delorderbtn = findViewById(R.id.delorderbtn);
        backbtn = findViewById(R.id.backbtn);
        //리사이클러뷰 사용을 위한 설정
        ingredientrecyclerView = (RecyclerView)findViewById(R.id.inputingview); //추가된 재료 현황을 리사이클러뷰를 사용해서 나타낸다
        ingredientrecyclerView.setHasFixedSize(true);
        ingredientlayoutManager = new LinearLayoutManager(this);
        ingredientrecyclerView.setLayoutManager(ingredientlayoutManager);
        orderrecyclerView = (RecyclerView)findViewById(R.id.inputorderview); //추가된 요리 설명 현황을 리사이클러뷰를 사용해서 나타낸다.
        orderrecyclerView.setHasFixedSize(true);
        orderlayoutManager = new LinearLayoutManager(this);
        orderrecyclerView.setLayoutManager(orderlayoutManager);
        inputorders = new ArrayList<order>();
        inputingredients = new ArrayList<ingredient>();
        ingorder = 1;
        cookorder =1;
        //데이터베이스 연결
        database = FirebaseDatabase.getInstance();
        recipeInfoReference = database.getReference("Recipe/RecipeInfo"); //파이어베이스 데이터베이스 RecipeInfo 연동
        orderReference = database.getReference("Recipe/RecipeOrder"); //파이어베이스 데이터베이스 RecipeInfo 연동
        ingredientReference = database.getReference("Recipe/RecipeMaterial"); //파이어베이스 데이터베이스 RecipeInfo 연동
        userReference = database.getReference("UserAccount");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user!=null?user.getUid():null;  // 현재 접속중인 user의 uid 가져오기


        backbtn.setOnClickListener(new View.OnClickListener(){
            //뒤로가기 버튼 구현
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addingbtn.setOnClickListener(new View.OnClickListener() {
            // 재료 추가 버튼을 누르면 재료 ArrayList에 추가 후 현황을 보여준다.
            // 만약 입력값이 충족되지 않았다면 Toast메세지를 출력해준다.
            @Override
            public void onClick(View view) {
                ingredient input= new ingredient();
                if(eingname.length()!=0 && eingamount.length()!=0 && eingtype.length()!=0){
                    input.setMaterial_name(eingname.getText().toString());
                    input.setMaterial_type(eingtype.getText().toString());
                    input.setMaterial_amount(eingamount.getText().toString());
                    input.setMaterial_order(ingorder);
                    ingorder++;
                    inputingredients.add(input);
                    inputingadapter = new ingredientRecyclerViewAdapter(inputingredients);
                    ingredientrecyclerView.setAdapter(inputingadapter); //리사이클러뷰에 어댑터 연결
                    eingname.setText("");
                    eingtype.setText("");
                    eingamount.setText("");
                }
                else {
                    Toast.makeText(writerecipe.this,"재료정보를 모두 입력해주세요",Toast.LENGTH_SHORT).show();

                }
            }
        });
        delingbtn.setOnClickListener(new View.OnClickListener() {
            // 재료 삭제 버튼을 누르면 Array에서 마지막을 없애고
            // 추가현황을 업데이트 해준다.
            @Override
            public void onClick(View view) {
                int length = inputingredients.size();
                if(length>0){
                    inputingredients.remove(length-1);
                    inputingadapter = new ingredientRecyclerViewAdapter(inputingredients);
                    ingredientrecyclerView.setAdapter(inputingadapter); //리사이클러뷰에 어댑터 연결
                    ingorder--;
                }
                else {
                    Toast.makeText(writerecipe.this,"추가된 재료가 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addorderbtn.setOnClickListener(new View.OnClickListener(){
            //순서 추가 버튼을 누를때 ArrayList에 추가 후 현황을 보여준다
            // 만약 입력값이 충족되지 않았다면 Toast메세지를 출력해준다.
            @Override
            public void onClick(View view) {
                order inputorder = new order();
                if(edescription.length()!=0){
                    inputorder.setOrder_description(edescription.getText().toString());
                    inputorder.setCook_order(cookorder);
                    cookorder++;
                    if(etip.length()!=0){
                        inputorder.setOrder_tip(etip.getText().toString());
                    }
                    else{
                        inputorder.setOrder_tip("null");
                    }
                    inputorders.add(inputorder);
                    inputorderadapter = new orderRecyclerViewAdapter(inputorders);
                    orderrecyclerView.setAdapter(inputorderadapter);
                    edescription.setText("");
                    etip.setText("");
                }
                else {
                    Toast.makeText(writerecipe.this,"요리설명을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        delorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = inputorders.size();
                if(length>0){
                    inputorders.remove(length-1);
                    inputorderadapter = new orderRecyclerViewAdapter(inputorders);
                    orderrecyclerView.setAdapter(inputorderadapter);
                    cookorder--;
                }
                else {
                    Toast.makeText(writerecipe.this,"추가된 요리설명이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addrecipebtn.setOnClickListener(new View.OnClickListener(){
            //모든 레시피 정보를 작성후 레시피 추가 버튼을 누르면 DB에 레시피 정보를 추가
            @Override
            public void onClick(View view) {
                if(inputingredients.size()>0&&inputorders.size()>0&& isInfofull()){
                    Recipe_item inputInfo = new Recipe_item();
                    RecipePost post = new RecipePost();
                    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    inputInfo.setRec_cnt(0);
                    int recipecode;
                    mNow = System.currentTimeMillis();
                    recipecode = (int)mNow * -1;
                    mDate = new Date(mNow);
                    inputInfo.setWrite_date( mFormat.format(mDate).toString());
                    inputInfo.setRecipe_code(recipecode); // 현재 시간을 millisecond로 반환하는값으로 각각 고유값을 가져야 하는 레시피 코드를 대체함.
                    inputInfo.setRecipe_intro(eintro.getText().toString());
                    inputInfo.setAmount(eamount.getText().toString());
                    inputInfo.setCooktime(etime.getText().toString());
                    inputInfo.setDifficulty(edifficulty.getText().toString());
                    inputInfo.setFood_type(efoodtype.getText().toString());
                    inputInfo.setType_name(efood.getText().toString());
                    inputInfo.setRecipe_name(erecipename.getText().toString());
                    post.setRecipe_code(recipecode);
                    userReference.child(uid).child("RecipePost").child(String.valueOf(recipecode)).setValue(post); // user 데이터베이스에 저장.
                    recipeInfoReference.child(String.valueOf(recipecode)).setValue(inputInfo); // recipecode를 키값으로 데이터베이스에 저장.
                    for(int i=0;i<inputingredients.size();i++){
                        inputingredients.get(i).setRecipe_code(recipecode);
                        inputingredients.get(i).setMaterial_code(recipecode+i);
                        ingredientReference.child(String.valueOf(recipecode+i)).setValue(inputingredients.get(i)); //재료추가
                    }
                    for(int i=0;i<inputorders.size();i++){
                        inputorders.get(i).setRecipe_code(recipecode);
                        orderReference.child(String.valueOf(recipecode)+i).setValue(inputorders.get(i)); //순서추가
                    }
                    Toast.makeText(writerecipe.this,mFormat.format(mDate).toString(),Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(writerecipe.this,"레시피 정보를 모두 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}