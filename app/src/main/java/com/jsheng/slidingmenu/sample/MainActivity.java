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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSlidingMenu.setSlidingViews(mMenuLayout, mContentLayout);


        ColorPagerAdapter adapter = new ColorPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.openMenu();
            }
        });
    }

    static class ColorPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        private final int[] COLORS = new int[] {
                R.color.red,
                R.color.green,
                R.color.blue,
                R.color.white,
                R.color.black
        };

        public ColorPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            for (int i = 0; i < COLORS.length; i++) {
                mFragments.add(ColorFragment.getInstance(i, COLORS[i]));
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
