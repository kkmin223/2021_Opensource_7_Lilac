package com.example.mandish_lilac;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
/*
작성자: 김강민
최종수정일: 21-05-17
설명: 상단 메뉴 탭으로 페이지를 이동하는 기능
 */
public class ViewPager2Adapter  extends FragmentStateAdapter {
    private String mSearchTerm;
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return FragRecipe.newInstance();
            case 1:
                return FragCommunity.newInstance();
            case 2:
                return FragMypage.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setTextQueryChanged(String newText){
        mSearchTerm = newText;
    }
}
