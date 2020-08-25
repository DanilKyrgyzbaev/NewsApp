package com.mad_devs.view.fragment.newsDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.util.Objects;


public class NewsDetailFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private ImageView imageView;
    private TextView appbar_title, appbar_subtitle, date, time, title;
    private boolean isHideToolbarView = false;
    private FrameLayout date_behavior;
    private LinearLayout titleAppbar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;
    private static NewsDetailFragment instance = null;
    private WebView webView;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    public static NewsDetailFragment getInstance(){
        if (instance == null){
            instance = new NewsDetailFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_news_detail , container , false );
//
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull ( getActivity () )).setSupportActionBar ( toolbar );
        Objects.requireNonNull ( ((AppCompatActivity) getActivity ()).getSupportActionBar () ).setTitle ( "" );
        Objects.requireNonNull ( ((AppCompatActivity) getActivity ()).getSupportActionBar () ).setDisplayHomeAsUpEnabled ( true );
        final CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        webView = view.findViewById(R.id.webView);
        appBarLayout = view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this );
        date_behavior = view.findViewById(R.id.date_behavior);
        titleAppbar = view.findViewById(R.id.title_appbar);
        imageView = view.findViewById(R.id.backdrop);
        appbar_title = view.findViewById(R.id.title_on_appbar);
        appbar_subtitle = view.findViewById(R.id.subtitle_on_appbar);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        title = view.findViewById(R.id.title);


        assert getArguments () != null;
        mUrl = getArguments ().getString ( "url" );
        mImg = getArguments ().getString ("img");
        mTitle = getArguments ().getString ( "title");
        mDate = getArguments ().getString ("date");
        mSource = getArguments ().getString("source");
        mAuthor = getArguments ().getString("author");


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
        time.setText ( mSource + author + " \u2022" + Utils.DateToTimeFormat ( Utils.getCountry () ));

        initWebView(mUrl);

        return view;
    }

//onSupportNavigateUp

    private void initWebView(String url){
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient ());
        webView.loadUrl(url);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu , @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu ( menu , inflater );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.view_web){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData( Uri.parse(mUrl));
            startActivity(i);
            return true;
        }

        else if (id == R.id.share){
            try{

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plan");
                i.putExtra(Intent.EXTRA_SUBJECT, mSource);
                String body = mTitle + "\n" + mUrl + "\n" + "Share from the News App" + "\n";
                i.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(i, "Share with :"));

            }catch (Exception e){
                Toast.makeText ( getContext () , "Hmm.. Sorry, \\nCannot be share" , Toast.LENGTH_SHORT ).show ();

            }
        }

        return super.onOptionsItemSelected ( item );
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout , int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            date_behavior.setVisibility(View.GONE);
            titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            date_behavior.setVisibility(View.VISIBLE);
            titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }
}