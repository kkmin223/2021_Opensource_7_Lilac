package com.example.mandish_lilac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecipeListViewAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {

    private ImageView foodImgView;
    private TextView nameTextView;
    private TextView introTextView;
    private TextView writerTextView;
    private TextView recTextView;
    private TextView dateTextView;
    private Context context;
    private ArrayList<Recipe_item> RecipeItemList = new ArrayList<Recipe_item>();

    public RecipeListViewAdapter(@NonNull Context context) {
        super(context,0);
        this.context = context;

    }


    public void addItem(String name,String intro, String writer,String url){
        Recipe_item item = new Recipe_item();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String getdate = sdf.format(date);
        item.setRecipe_name(name);
        item.setRecipe_intro(intro);
        item.setWriter(writer);
        item.setImg_url(url);
        item.setRec_cnt(0);
        item.setWrite_date(getdate);
        RecipeItemList.add(item);
    }


    @Override
    public int getCount() {
        return RecipeItemList.size();
    }

    @Override
    public Recipe_item getItem(int i) {
        return RecipeItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
         /*
        작성자: 김강민
        최종수정일: 21-05-19
        설명: 레시피 기본정보를 리스트 뷰에 출력시키기 위한 함수
        */
        //"recipelist_item" Layout을 inflate하여 converView 참조 획득.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recipelist_item,parent,false);
        }
        // 화면에 표시될 View로 부터 위젯의 id로 참조를 획득한다.
        foodImgView = (ImageView) convertView.findViewById(R.id.food_img);
        nameTextView = (TextView) convertView.findViewById(R.id.recipe_name);
        introTextView = (TextView) convertView.findViewById(R.id.recipe_intro);
        writerTextView = (TextView) convertView.findViewById(R.id.recipe_writer);
        recTextView = (TextView) convertView.findViewById(R.id.recipe_rec);
        dateTextView = (TextView) convertView.findViewById(R.id.wirte_date);
        final Recipe_item recipe_item = RecipeItemList.get(i);
        // 리스트 아이템 화면에 레시피 정보를 출력시킨다.
        Glide.with(convertView)
                .load("https://ifh.cc/g/CCLQCi.jpg")
                .thumbnail(0.3f)
                .error(R.drawable.mandish_logo2)
                .fitCenter()
                .into(foodImgView);
        nameTextView.setText(recipe_item.getRecipe_name());
        introTextView.setText(recipe_item.getRecipe_intro());
        writerTextView.setText(recipe_item.getWriter());
        recTextView.setText("추천수: "+String.valueOf(recipe_item.getRec_cnt()));
        dateTextView.setText("작성시간: "+recipe_item.getWrite_date());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
    }


}

