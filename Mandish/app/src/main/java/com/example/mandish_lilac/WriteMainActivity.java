package com.example.mandish_lilac;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WriteMainActivity extends AppCompatActivity {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private EditText mWriteTitletext;
    private EditText mWriteContentstext;
    private String id;
    private String getTime;
    private Intent intent;
    private String name2;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_main);

        findViewById(R.id.btn3).setOnClickListener(this::onClick);

        mWriteContentstext = findViewById(R.id.write_contents_text);
        mWriteTitletext = findViewById(R.id.write_title_text);

        name2 = "abcd";

    }

    public void onClick(View v) {
        id = mStore.collection("board").document().getId();

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = simpleDate.format(mDate);
        Map<String, Object> post = new HashMap<>();
        post.put("id", id);
        post.put("title", mWriteTitletext.getText().toString());
        post.put("contents", mWriteContentstext.getText().toString());
        post.put("name", name2);
        post.put("time",getTime);

        mStore.collection("board").document(id).set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(WriteMainActivity.this, "업로드 성공!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(WriteMainActivity.this, "업로드 실패!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

