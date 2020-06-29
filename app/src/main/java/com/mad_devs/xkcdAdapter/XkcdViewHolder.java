package com.mad_devs.xkcdAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mad_devs.Interface.ItemClickListener;
import com.mad_devs.newsapp.R;

public class XkcdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public TextView txtTitle,txtPubDate,txtContent;
    public ImageView image_top;
    private ItemClickListener itemClickListenerX;


    public XkcdViewHolder(@NonNull View itemView) {
        super ( itemView );
        txtTitle = itemView.findViewById( R.id.txtTitle);
        txtPubDate = itemView.findViewById(R.id.txtPubDate);
        txtContent = itemView.findViewById(R.id.txtContent);
        image_top = itemView.findViewById(R.id.image_top);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListenerX = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListenerX.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListenerX.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
