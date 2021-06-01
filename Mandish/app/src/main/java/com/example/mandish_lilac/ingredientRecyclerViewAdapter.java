package com.example.mandish_lilac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ingredientRecyclerViewAdapter extends RecyclerView.Adapter<ingredientRecyclerViewAdapter.ViewHolder> {
    /*
    작성자: 김강민
    설명: 레시피 세부사항에
    재료들을 출력하기 위한 어댑터
    */
    private ArrayList<ingredient> ingredientList= new ArrayList<ingredient>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView type;
        private TextView amount;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = (TextView) view.findViewById(R.id.ing_name);
            type = (TextView) view.findViewById(R.id.ing_type);
            amount = (TextView) view.findViewById(R.id.ing_amount);
        }

    }
    public ingredientRecyclerViewAdapter(ArrayList<ingredient> ingredients){
        ingredientList = ingredients;
    }
    @NonNull
    @Override
    public ingredientRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipeingrdients_item,parent,false);
        ingredientRecyclerViewAdapter.ViewHolder holder = new ingredientRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(ingredientList.get(position).getMaterial_name());
        holder.type.setText(ingredientList.get(position).getMaterial_type());
        holder.amount.setText(ingredientList.get(position).getMaterial_amount());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
