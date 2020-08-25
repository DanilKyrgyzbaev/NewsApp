package com.mad_devs.view.fragment.news;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mad_devs.OnItemClickListener;
import com.mad_devs.data.model_Bitcoin.Article;
import com.mad_devs.internet.R;
import com.mad_devs.view.fragment.news.ui.AdapterBitcoin;
import com.mad_devs.view.fragment.news.ui.AdapterTopNews;
import com.mad_devs.view.fragment.newsDetail.NewsDetailFragment;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements OnItemClickListener {

    private NewsViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_top_news;
    private AdapterBitcoin adapterBitcoin;
    private AdapterTopNews adapterTopNews;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    private List<Article> articles = new ArrayList<> ();



    private static final String apikey = "bf583f2d314342d9b410aaac8e4a23df";
    private static final String q = "apple";

    public static NewsFragment newInstance() {
        return new NewsFragment ();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container ,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.news_fragment , container , false );

        Toolbar toolbart = view.findViewById ( R.id.toolbar1 );
        ((AppCompatActivity)getActivity ()).setSupportActionBar ( toolbart );
        recyclerView = view.findViewById ( R.id.recycler_view );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext (),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        setHasOptionsMenu ( true );
        recyclerView_top_news = view.findViewById ( R.id.recyclerView_top_news );
        LinearLayoutManager linearLayoutManagerTopNews = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_top_news.setLayoutManager(linearLayoutManagerTopNews);
        mViewModel = ViewModelProviders.of ( this ).get ( NewsViewModel.class );
        setupRecyclerView ();
        getNewsBitkoin ();
        getTopNews ();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        // TODO: Use the ViewModel


    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu , @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu ( menu , inflater );
        inflater.inflate ( R.menu.menu_news, menu );
        SearchManager searchManager = (SearchManager) getActivity ().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem ( R.id.action_search );
        SearchView searchView = null;
        if (searchMenuItem != null){
            searchView = (SearchView) searchMenuItem.getActionView ();
//            initListener ();
        }
        if (searchView != null){
            searchView.setSearchableInfo ( searchManager.getSearchableInfo ( getActivity ().getComponentName ()));
        }
        
        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNewsSearch ( query );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
        searchMenuItem.getIcon ().setVisible ( false,false );

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected ( item );

    }

    private void getNewsSearch(String q){
        mViewModel.getSearchNews ( apikey,q );
        mViewModel.exampleMutableLiveData.observe ( getViewLifecycleOwner (),example -> {
            adapterBitcoin.update ( example.getArticles ());
        });
    }

    private void getNewsBitkoin(){
        mViewModel.getNewsBitkoin ( apikey,q );

        mViewModel.articleMutableLiveData.observe ( getViewLifecycleOwner() , example -> {
            Log.e ( "news Bitkoin" ,   "" );
            articles = example.getArticles ();
            adapterBitcoin.update ( example.getArticles () );

        } );
    }

    private void getTopNews(){
        mViewModel.getTopNews (apikey);
        mViewModel.topNewsMutableLiveData.observe ( getViewLifecycleOwner (), topNews -> {
            adapterTopNews.update ( topNews.getArticles () );
        }  );
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize ( true );
        recyclerView_top_news.setHasFixedSize ( true );
        adapterBitcoin = new AdapterBitcoin ();
        adapterBitcoin.initListener ( this );
        recyclerView.setAdapter ( adapterBitcoin );
        adapterTopNews = new AdapterTopNews ();
        recyclerView_top_news.setAdapter(adapterTopNews);
    }

    @Override
    public void onItemClickListener(View v , int position) {
        Log.e ( "lololo", position + "" );
        NewsDetailFragment newsDetailFragment = NewsDetailFragment.getInstance ();
        Bundle bundle = new Bundle();
        bundle.putString ( "url",articles.get ( position ).getUrl ());
        bundle.putString ( "title",articles.get ( position ).getTitle ());
        bundle.putString ( "img",articles.get ( position ).getUrlToImage ());
        bundle.putString ( "date",articles.get ( position ).getPublishedAt ());
        bundle.putString ( "source", articles.get ( position ).getSource ().getName ());
        bundle.putString ( "author", articles.get ( position ).getAuthor ());
        newsDetailFragment.setArguments ( bundle );
        FragmentTransaction transaction = getActivity ().getSupportFragmentManager ().beginTransaction ();
        transaction.replace ( R.id.fragmentContainer,newsDetailFragment );
        transaction.commit ();

    }
}
