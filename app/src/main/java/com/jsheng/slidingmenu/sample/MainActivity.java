package com.jsheng.slidingmenu.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jsheng.slidingmenu.SlidingMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SlidingMenu.SlidingListener {

    private static final String TAG = MainActivity.class.getSimpleName();

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

    private ColorPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSlidingMenu.setSlidingViews(mMenuLayout, mContentLayout);
        mSlidingMenu.addSlidingListener(this);

        mAdapter = new ColorPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position " + position);
                setSlidingMenuInterceptType(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (mSlidingMenu.isLtrDirection()) {
            mViewPager.setCurrentItem(0);
        } else {
            mViewPager.setCurrentItem(mAdapter.getCount() - 1);
        }
        mSlidingMenu.setInterceptAll(true);


        mMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.openMenu();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void setSlidingMenuInterceptType(int posViewPager) {
        if (mSlidingMenu.isLtrDirection()) {
            mSlidingMenu.setInterceptAll(posViewPager == 0);
        } else {
            mSlidingMenu.setInterceptAll(posViewPager == mAdapter.getCount() - 1);
        }
    }

    @Override
    public void onMenuSlide(View menuView, float slideOffset) {
        Log.d(TAG, "onMenuSlide, slideOffset " + slideOffset);
    }

    @Override
    public void onMenuOpened(View menuView) {
        Log.d(TAG, "onMenuOpened");
    }

    @Override
    public void onMenuClosed(View menuView) {
        Log.d(TAG, "onMenuClosed");
    }

    static class ColorPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        private final int[] COLORS = new int[]{
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSlidingMenu.removeSlidingListener(this);
    }
}
