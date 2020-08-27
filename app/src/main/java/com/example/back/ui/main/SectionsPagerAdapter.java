package com.example.back.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.back.R;
import com.example.back.fragments.FragmentContact;
import com.example.back.fragments.FragmentExtras;
import com.example.back.fragments.FragmentHome;
import com.example.back.fragments.FragmentNotification;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment= new FragmentHome();
                break;
            case 1:
                fragment=new FragmentContact();
                break;
            case 2:
                fragment=new FragmentExtras();
                break;
            case 3:
                fragment=new FragmentNotification();
                break;

        }return fragment;
    }



    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}