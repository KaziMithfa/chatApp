package com.example.chatapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessorAdapter tabAccessorAdapter;

    private FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("ChatApp");

        viewPager = findViewById(R.id.main_tabs_pager);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAccessorAdapter);

        tabLayout = findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);







    }

    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser == null)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }


    }
}
