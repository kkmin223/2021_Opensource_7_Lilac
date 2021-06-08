package com.example.mandish_lilac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
/*
작성자: 김강민
설명: xml을 연결해주어서 메뉴 이동을 가능하게함
 */

public class FragMypage extends Fragment {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseref;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView profile_id,profile_nic,profile_name,profile_write;
    private View view;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid=user!=null?user.getUid():null;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static FragMypage newInstance() {
        FragMypage fragMypage = new FragMypage();

        return fragMypage;
    }




    @Nullable
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                //view = inflater.inflate(R.layout.frag_mypage, container,false);

                view = inflater.inflate(R.layout.frag_mypage, container,false);
                mDatabase = FirebaseDatabase.getInstance();

                mDatabaseref = mDatabase.getReference("UserAccount");

                mDatabaseref.child(uid).child("emailId").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase","Error getting data",task.getException());

                }
                else{
                    Log.d("firebase",String.valueOf(task.getResult().getValue()));
                    profile_id.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });
                mDatabaseref.child(uid).child("nic").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase","Error getting data",task.getException());

                }
                else{
                    Log.d("firebase",String.valueOf(task.getResult().getValue()));
                    profile_nic.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });mDatabaseref.child(uid).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase","Error getting data",task.getException());

                }
                else{
                    Log.d("firebase",String.valueOf(task.getResult().getValue()));
                    profile_name.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });mDatabaseref.child(uid).child("Write").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase","Error getting data",task.getException());

                }
                else{
                    Log.d("firebase",String.valueOf(task.getResult().getValue()));
                    profile_write.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });


                profile_id=  (TextView) view.findViewById(R.id.profile_id);
                profile_nic= (TextView) view.findViewById(R.id.profile_nic);
                profile_name= (TextView) view.findViewById(R.id.profile_name);
                profile_write=(TextView)view.findViewById(R.id.profile_write);



                Button btnFollowers = (Button)view.findViewById(R.id.btnFollowers);
                btnFollowers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), followers.class);
                        startActivity(intent);
                    }
                });

                Button btnFavorites = (Button)view.findViewById(R.id.btnFavorites);
                btnFavorites.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), favorites.class);
                        startActivity(intent);
                    }
                });

                Button btnRecord = (Button)view.findViewById(R.id.btnRecord);
                btnRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), record.class);
                        startActivity(intent);
                    }
                });

                Button btnService = (Button)view.findViewById(R.id.btnService);
                btnService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), change.class);
                        startActivity(intent);
                    }
                });

                return view;
        }

}

