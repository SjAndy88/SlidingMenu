package com.jsheng.slidingmenu.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shengjun on 2016/12/25.
 */

public class MainFragment extends Fragment {

    private final static String KEY_INDEX = "key_index";
    private final static String KEY_COLOR = "key_color";

    @BindView(R.id.fragment_content)
    FrameLayout mContent;
    @BindView(R.id.fragment_text)
    TextView mText;

    private int index;
    private int color;

    public static MainFragment getInstance(int index, int color) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        args.putInt(KEY_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            index = args.getInt(KEY_INDEX);
            color = args.getInt(KEY_COLOR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.view_pager_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        view.setTag(color);
        mContent.setBackgroundColor(color);
        mText.setText(index+"");
    }
}
