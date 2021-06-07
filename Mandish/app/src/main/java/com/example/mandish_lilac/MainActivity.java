package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecipeRecyclerViewAdapter adapter;
    private ViewPager2Adapter pager2Adapter;
    private MenuItem searchMenuItem;

    Toolbar toolbar;
    ArrayList<ISearch> iSearch = new ArrayList<>();
    private String newText;
    ArrayList<String> listData = null;
    IDataCallback iDataCallback = null;

    public void setiDataCallback(IDataCallback iDataCallback) {
        this.iDataCallback = iDataCallback;
        iDataCallback.onFragmentCreated(listData);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        작성자: 김강민
        최종수정일: 21-05-17
        설명: 뷰 페이저2와 탭 레이아웃을 이용하여 상단 메뉴바 기능 구현
        */
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        ViewPager2Adapter fgAdapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(fgAdapter);
        final List<String> tabElement = Arrays.asList("레시피","커뮤니티","마이페이지");
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(tabElement.get(position));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTextColor(Color.BLACK);
                tab.setCustomView(textView);
            }
        }).attach();

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public void getDataFromFragment_one(ArrayList<Recipe_item> listData) {
        this.listData = listData;
        Log.e("-->", "" + listData.toString());
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        this.newText = newText;
        pager2Adapter.setTextQueryChanged(newText);

        for (ISearch iSearchLocal : this.iSearch)
            iSearchLocal.onTextQuery(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;*/
    }


