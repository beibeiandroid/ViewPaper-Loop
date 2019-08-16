package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager myViewPager;
    private List<ReadFragment> mListViews;
    private List<String> dataList=new ArrayList<>();

    private boolean mIsChanged = false;
    private static int POINT_LENGTH = 3;
    private static final int FIRST_ITEM_INDEX = 1;
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViews = new ArrayList<ReadFragment>();

        dataList.add("A");
        dataList.add("B");
        dataList.add("C");
        dataList.add("D");
        dataList.add("E");

        if(dataList.size()==1)
        {
            mListViews.add(ReadFragment.getInstance(dataList.get(0)));
        }else {
            for(int i=0;i<dataList.size();i++)
            {
                if(i==0)
                {
                    mListViews.add(ReadFragment.getInstance(dataList.get(dataList.size()-1)));
                }
                mListViews.add(ReadFragment.getInstance(dataList.get(i)));

                if(i==dataList.size()-1)
                {
                    mListViews.add(ReadFragment.getInstance(dataList.get(0)));
                }
            }
        }

        POINT_LENGTH = mListViews.size()>2?mListViews.size()-2:mListViews.size();

        myViewPager = (ViewPager) findViewById(R.id.viewPaper);
        myViewPager.setAdapter(MyPagerAdapter);
        myViewPager.setCurrentItem(1, false);
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pPosition) {
                mIsChanged = true;
                if (pPosition > POINT_LENGTH) {// 末位之后，跳转到首位（1）
                    mCurrentPagePosition = FIRST_ITEM_INDEX;
                } else if (pPosition < FIRST_ITEM_INDEX) {// 首位之前，跳转到末尾（N）
                    mCurrentPagePosition = POINT_LENGTH;
                } else {
                    mCurrentPagePosition = pPosition;
                }
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }


            @Override
            public void onPageScrollStateChanged(int pState) {
                if (ViewPager.SCROLL_STATE_IDLE == pState) {
                    if (mIsChanged) {
                        mIsChanged = false;
                        myViewPager.setCurrentItem(mCurrentPagePosition, false);
                    }
                }
            }
        });
    }


    private FragmentPagerAdapter MyPagerAdapter = new FragmentPagerAdapter(
            getSupportFragmentManager()) {


        @Override
        public int getCount() {
            return mListViews.size();
        }


        @Override
        public Fragment getItem(int arg0) {
            return mListViews.get(arg0);
        }
    };

}

