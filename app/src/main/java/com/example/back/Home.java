package com.example.back;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.back.Adapters.ViewAdapter;
import com.example.back.fragments.FragmentContact;
import com.example.back.fragments.FragmentExtras;
import com.example.back.fragments.FragmentHome;
import com.example.back.fragments.FragmentNotification;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<String> array=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.toolbarHome);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.home_viewpager);

        //add titles to array list
        array.add("home");
        array.add("contact");
        array.add("extras");
        array.add("notification");

        //call to prepare viewpager

        tabLayout.setupWithViewPager(viewPager);

        //set toolbar as our actionbar
        setSupportActionBar(toolbar);
    }

    public void prepareViewpager(ViewPager viewPager,ArrayList<String> array){
        ViewAdapter viewAdapter=new ViewAdapter(getSupportFragmentManager());

        //get fragments
        FragmentHome fragmentHome=new FragmentHome();
        FragmentContact fragmentContact=new FragmentContact();
        FragmentExtras fragmentExtras=new FragmentExtras();
        FragmentNotification fragmentNotification=new FragmentNotification();

        //switch case
        for (int i=0;i < array.size(); i++){
            Bundle bundle=new Bundle();
            bundle.putString("title",array.get(i));
            fragmentHome.setArguments(bundle);
            viewAdapter.addFragment(fragmentHome,array.get(i));
        }
        viewPager.setAdapter(viewAdapter);
        }
}
