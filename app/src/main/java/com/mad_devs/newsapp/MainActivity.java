package com.mad_devs.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mad_devs.category.CategoryFragment;
import com.mad_devs.news.NewsFragment;
import com.mad_devs.saved.SavedFragment;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Document document;
    private Thread secThread;
    private Runnable runnable;
    private MyTask myTask;
    private RecyclerView recyclerViewNews;


    private final String RSS_link = "http://feeds.bbci.co.uk/news/rss.xml";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        bottomNavigationView = findViewById ( R.id.bottomNavigation );
        bottomNavigationView.setOnNavigationItemSelectedListener ( navigationItemSelectedListener );
        getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragmentContainer , new NewsFragment () ).commit ();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener () {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId ()) {
                case R.id.news_menu:
                    selectedFragment = new NewsFragment ();
                    break;
                case R.id.category_menu:
                    selectedFragment = new CategoryFragment ();
                    break;
                case R.id.saved_menu:
                    selectedFragment = new SavedFragment ();
                    break;
            }
            getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragmentContainer , selectedFragment ).commit ();
            return true;
        }
    };




}
