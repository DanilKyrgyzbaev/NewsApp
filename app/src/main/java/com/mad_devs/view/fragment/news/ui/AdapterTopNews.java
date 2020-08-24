package com.mad_devs.view.fragment.news.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mad_devs.data.model_top_News.Article;
import com.mad_devs.internet.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTopNews extends RecyclerView.Adapter<TopNewsViewHolder> {

    private ArrayList<Article> arrayList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public TopNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        context = parent.getContext ();
        View view = LayoutInflater.from(parent.getContext ()).inflate( R.layout.news_top_item , parent,false);
        return new TopNewsViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull TopNewsViewHolder holder , int position) {
        holder.onBind ( arrayList.get ( position ), context );
    }

    @Override
    public int getItemCount() {
        return arrayList.size ();
    }

    public void update(List<com.mad_devs.data.model_top_News.Article> articles){
        arrayList.clear();
        arrayList.addAll(articles);
        notifyDataSetChanged ();
    }
}
