package com.mad_devs.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mad_devs.category.CategoryFragment;
import com.mad_devs.news.NewsFragment;
import com.mad_devs.saved.SavedFragment;

public class MainActivity extends AppCompatActivity {

     BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        bottomNavigation ();
    }

    private void bottomNavigation(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NewsFragment()).commit();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId ()) {
                    case R.id.news_menu:
                        fragment = new NewsFragment ();
                        break;
                    case R.id.category_menu:
                        fragment = new CategoryFragment ();
                        break;
                    case R.id.saved_menu:
                        fragment = new SavedFragment ();
                        break;
                }
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragmentContainer,fragment ).commit ();
                return true;
            }
        } );
    }
}
