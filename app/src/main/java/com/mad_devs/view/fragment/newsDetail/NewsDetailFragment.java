package com.mad_devs.view.fragment.newsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mad_devs.Utils;
import com.mad_devs.internet.R;


public class NewsDetailFragment extends Fragment {

    private ImageView imageView;
    private TextView appbar_title, appbar_subtitle, date, time, title;
    private boolean isHideToolbarView = false;
    private FrameLayout date_behavior;
    private LinearLayout titleAppbar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;


    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_news_detail , container , false );

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity ()).setSupportActionBar ( toolbar );
        ((AppCompatActivity)getActivity ()).getSupportActionBar ().setTitle ( "" );
        ((AppCompatActivity)getActivity ()).getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        final CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener( (AppBarLayout.BaseOnOffsetChangedListener) this );
        date_behavior = view.findViewById(R.id.date_behavior);
        titleAppbar = view.findViewById(R.id.title_appbar);
        imageView = view.findViewById(R.id.backdrop);
        appbar_title = view.findViewById(R.id.title_on_appbar);
        appbar_subtitle = view.findViewById(R.id.subtitle_on_appbar);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        title = view.findViewById(R.id.title);


        Intent intent = getActivity ().getIntent ();
        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mSource = intent.getStringExtra("source");
        mAuthor = intent.getStringExtra("author");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error( Utils.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition( DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        appbar_title.setText(mSource);
        appbar_subtitle.setText(mUrl);
        date.setText(Utils.DateFormat(mDate));
        title.setText(mTitle);

        String author;
        if (mAuthor != null){
            author = " \u2022 " + mAuthor;
        } else {
            author = "";
        }

        time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));

//        initWebView(mUrl);

        return view;
    }
}