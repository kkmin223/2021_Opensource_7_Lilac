package com.example.mandish_lilac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

    /*
    작성자: 김강민
    설명: 레시피 세부사항에 
    요리 순서를 출력하기 위한 리사이클러뷰 어댑터
    */

public class orderRecyclerViewAdapter extends RecyclerView.Adapter<orderRecyclerViewAdapter.ViewHolder> {
    private ArrayList<order> orderList= new ArrayList<order>();



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private TextView tip;
        private ImageView order_img;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            description = (TextView) view.findViewById(R.id.description);
            tip = (TextView) view.findViewById(R.id.tip);
        }

    }





    public orderRecyclerViewAdapter(ArrayList<order> OrderList){
        orderList = OrderList;

    }
    @NonNull
    @Override
    public orderRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipeorder_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.description.setText(orderList.get(position).getCook_order()+". "+orderList.get(position).getOrder_description());
        if(!orderList.get(position).getOrder_tip().contains("null")){
            holder.tip.setText("Tip: "+orderList.get(position).getOrder_tip());
        }
        else{
            holder.tip.setText(" ");
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
