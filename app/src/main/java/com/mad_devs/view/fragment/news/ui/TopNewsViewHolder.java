package com.mad_devs.view.fragment.news.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mad_devs.data.model_top_News.Article;
import com.mad_devs.internet.R;

public class TopNewsViewHolder extends RecyclerView.ViewHolder {
    Context context;
    private TextView txtTitleTop;
//    private TextView txtDescriptionTop;
    private ImageView imageTop;

    public TopNewsViewHolder(@NonNull View itemView) {
        super ( itemView );
        txtTitleTop = itemView.findViewById ( R.id.txtTitleTop );
        imageTop = itemView.findViewById ( R.id.imageTop );
//        txtDescriptionTop = itemView.findViewById ( R.id.txtDescriptionTop );
    }

    @SuppressLint("CheckResult")
    public void onBind(Article article, Context context) {
        this.context = context;
        txtTitleTop.setText ( article.getTitle () );
//        txtDescriptionTop.setText (article.getDescription ());
        if (context != null){
            Glide.with(context).load ( article.getUrlToImage () ).into(imageTop);
        }

    }


}
