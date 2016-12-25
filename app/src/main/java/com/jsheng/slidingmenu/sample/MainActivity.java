package com.jsheng.slidingmenu.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jsheng.slidingmenu.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_layout)
    SlidingMenu mSlidingMenu;

    @BindView(R.id.menu_layout)
    FrameLayout mMenuLayout;

    @BindView(R.id.content_layout)
    FrameLayout mContentLayout;

    @BindView(R.id.content_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.menu_icon)
    ImageView mMenuIcon;

    private static List<Integer> colors = new ArrayList<>();

    static {
        colors.add(R.color.colorYellow);
        colors.add(R.color.colorBlue);
        colors.add(R.color.colorGray);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSlidingMenu.setSlidingViews(mMenuLayout, mContentLayout);


        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.openMenu();
            }
        });
    }

    static class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainFragment.getInstance(position, colors.get(position));
        }

        @Override
        public int getCount() {
            return colors.size();
        }
    }
}
