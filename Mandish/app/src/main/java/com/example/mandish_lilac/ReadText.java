package com.example.mandish_lilac;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReadText extends AppCompatActivity {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private Intent intent;
    private TextView mReadTitletext;
    private TextView mReadContentstext;
    private TextView mReadNametext;
    private String id;
    private String title;
    private String content;
    private String name;
    private String title2;
    private String content2;
    private String name2;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_text);

        intent = getIntent();
        content = intent.getStringExtra("content");
        title = intent.getStringExtra("title");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        if (user != null){
            name2 = user.getEmail();
        }


        mReadContentstext = findViewById(R.id.read_contents_text);
        mReadNametext = findViewById(R.id.read_name_text);
        mReadTitletext = findViewById(R.id.read_title_text);

        mReadTitletext.setText(title);
        mReadContentstext.setText(content);
        mReadNametext.setText(name);


    }

    public void onClick(View v) {
        intent.putExtra("title",mReadTitletext.getText().toString());
        intent.putExtra("content",mReadContentstext.getText().toString());
        intent.putExtra("name",mReadNametext.getText().toString());
        intent.putExtra("id",id);
        v.getContext().startActivity(intent);
        finish();
    }
}

