package com.mad_devs.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mad_devs.Adapter.FeedAdapter;
import com.mad_devs.cammon.HTTPDataHandler;
import com.mad_devs.model.RSSObject;
import com.mad_devs.newsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    RecyclerView recyclerView;
    RSSObject rssObject;

    private final String RSS_link="http://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_news , container , false );
        recyclerView = view.findViewById(R.id.recyclerView_news);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext (),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager ( linearLayoutManager );
        loadRSS();
        return view;
    }

    private void loadRSS() {

        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
//                mDialog.setMessage("Please wait...");
//                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            loadRSS();
        return true;
    }


}