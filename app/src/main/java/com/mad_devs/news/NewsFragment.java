package com.mad_devs.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.mad_devs.Adapter.FeedAdapter;
import com.mad_devs.cammon.HTTPDataHandler;
import com.mad_devs.model.RSSObject;
import com.mad_devs.modelTechnology.Example;
import com.mad_devs.newsapp.R;
import com.mad_devs.technologyAdapter.TechnologyAdapter;

public class NewsFragment extends Fragment {
    RecyclerView recyclerView, recyclerViewTechnology;
    RSSObject rssObject;
    Example exampleTechnology;
    // your API key is: bf583f2d314342d9b410aaac8e4a23df

    private final String RSS_link="http://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    private final String RSS_linkTechnology="https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
    private final String RSS_to_Json_API_Technology = " https://api.rss2json.com/v1/api.json?rss_url=";


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_news , container , false );
        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        activity.setSupportActionBar(toolbar);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("text");
        Context context;
        recyclerView = view.findViewById(R.id.recyclerView_news);
        recyclerViewTechnology = view.findViewById(R.id.recyclerView_top_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext (),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager ( linearLayoutManager);
        LinearLayoutManager linearLayoutManagerTechnology = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTechnology.setLayoutManager(linearLayoutManagerTechnology);
        loadRSS();
        loadRSSTechnology ();
        return view;
    }


    private void loadRSS() {

        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
//                mDialog.setMessage("Please wait...");
//                mDialog.show();
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(String s) {
//                mDialog.dismiss();
                rssObject = new Gson ().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
    }

    private void loadRSSTechnology() {

        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
//                mDialog.setMessage("Please wait...");
//                mDialog.show();
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(String s) {
//                mDialog.dismiss();
                exampleTechnology = new Gson ().fromJson (s,Example.class);
                TechnologyAdapter technologyAdapter = new TechnologyAdapter ( exampleTechnology,getContext ());
                recyclerViewTechnology.setAdapter(technologyAdapter);
                technologyAdapter.notifyDataSetChanged ();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API_Technology);
        url_get_data.append(RSS_linkTechnology);
        loadRSSAsync.execute(url_get_data.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            loadRSS();
            loadRSSTechnology ();
        return true;
    }

}