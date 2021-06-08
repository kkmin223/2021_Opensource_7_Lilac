package com.example.mandish_lilac;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
작성자: 김주엽
최종수정일: 21-06-01
설명: xml을 연결해주어서 메뉴 이동을 가능하게하고 글목록을 보여주고 글쓰기기능과 글을 클릭시 내용이 보여
지게 된다.
 */
public class FragCommunity extends Fragment implements View.OnClickListener {

    private View v;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mMainRecyclerView;
    private MainAdapter adapter2;
    private RecyclerView.LayoutManager layoutManager;
    private List<Write_item> WriteList;
    private Intent intent2;
    private String name2;
    private String str;
    private String id;


    public static FragCommunity newInstance() {
        FragCommunity fragCommunity = new FragCommunity();
        return fragCommunity;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_community, container,false);

        ImageButton btn = v.findViewById(R.id.main_write_button);
        btn.setOnClickListener(this);



        mMainRecyclerView = (RecyclerView) v.findViewById(R.id.WriteListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mMainRecyclerView.setLayoutManager(layoutManager);
        mMainRecyclerView.setHasFixedSize(true);


        WriteList = new ArrayList<>();
        mStore.collection("board").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange document : queryDocumentSnapshots.getDocumentChanges()) {
                    String id = (String) document.getDocument().getData().get("id");
                    String title = (String) document.getDocument().getData().get("title");
                    String contents = (String) document.getDocument().getData().get("contents");
                    String name = (String) document.getDocument().getData().get("name");
                    String time = (String) document.getDocument().getData().get("time");
                    Write_item data = new Write_item(id, title, contents, name, time);
                    WriteList.add(data);
                }
                adapter2 = new MainAdapter(WriteList);
                mMainRecyclerView.setAdapter(adapter2);
            }});
        return v;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(v.getContext(), WriteMainActivity.class);
        v.getContext().startActivity(intent);
    }


    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        private List<Write_item> WriteList;
        public MainAdapter(List<Write_item> WriteList) {
            this.WriteList = WriteList;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            Write_item data = WriteList.get(position);
            holder.mTitleTextView.setText(data.getTitle());
            holder.mNameTextView.setText(data.getName());
            holder.mTimeTextView.setText(data.getTime());

        }

        @Override
        public int getItemCount() {
            return WriteList.size();
        }

        public class MainViewHolder extends RecyclerView.ViewHolder {

            private TextView mTitleTextView;
            private TextView mNameTextView;
            private TextView mTimeTextView;


            public MainViewHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(),ReadText.class);
                        intent.putExtra("title",WriteList.get(getPosition()).getTitle());
                        intent.putExtra("content",WriteList.get(getPosition()).getContents());
                        intent.putExtra("name",WriteList.get(getPosition()).getContents());
                        intent.putExtra("id",WriteList.get(getPosition()).getId());
                        v.getContext().startActivity(intent);
                    }
                });

                this.mTitleTextView = itemView.findViewById(R.id.item_title_text);
                this.mNameTextView = itemView.findViewById(R.id.item_name_text);
                this.mTimeTextView = itemView.findViewById(R.id.item_time_text);

            }
        }
    }
}
