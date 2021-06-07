package com.example.mandish_lilac;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Recipe_item> RecipeList;
    private ArrayList<Recipe_item> RecipeListFull;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public RecipeRecyclerViewAdapter(ArrayList<Recipe_item> recipe_List) {
        RecipeList = recipe_List;
        RecipeListFull = new ArrayList<>(recipe_List);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipelist_item,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        storage = FirebaseStorage.getInstance("gs://mandish-93a90.appspot.com"); //파이어베이스 스토리지 연동
        storageReference = storage.getReference(); // 스토리지 테이블 연동
        if(RecipeList.get(position).getImg_url()==null){
            //db에 이미지가 없다면 스토리지에서 가져온다
            storageReference.child("FoodInfo/f_"+RecipeList.get(position).getRecipe_code()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(holder.foodImgView)
                            .load(uri)
                            .thumbnail(0.3f)
                            .dontAnimate()
                            .error(R.drawable.mandish_logo2)
                            .into(holder.foodImgView);
                }
            });
        }
        else {
            Glide.with(holder.foodImgView)
                    .load(RecipeList.get(position).getImg_url())
                    .thumbnail(0.3f)
                    .dontAnimate()
                    .error(R.drawable.mandish_logo2)
                    .fitCenter()
                    .into(holder.foodImgView);
        }

        holder.nameTextView.setText(RecipeList.get(position).getRecipe_name());
        holder.introTextView.setText(RecipeList.get(position).getRecipe_intro());
        holder.writerTextView.setText(RecipeList.get(position).getWriter());
        holder.recTextView.setText(String.valueOf(RecipeList.get(position).getRec_cnt()));
        holder.dateTextView.setText(RecipeList.get(position).getWrite_date());

    }
    @Override
    public int getItemCount() {
        return (RecipeList != null ? RecipeList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImgView;
        TextView nameTextView;
        TextView introTextView;
        TextView writerTextView;
        TextView recTextView;
        TextView dateTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.foodImgView = itemView.findViewById(R.id.food_img);
            this.nameTextView = itemView.findViewById(R.id.recipe_name);
            this.introTextView = itemView.findViewById(R.id.recipe_intro);
            this.writerTextView = itemView.findViewById(R.id.recipe_writer);
            this.recTextView = itemView.findViewById(R.id.recipe_rec);
            this.dateTextView = itemView.findViewById(R.id.wirte_date);
        }
    }

    /*@Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Recipe_item> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(RecipeListFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Recipe_item item : RecipeListFull){
                    if (item.getRecipe_name().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            RecipeList.clear();
            RecipeList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };*/
}
