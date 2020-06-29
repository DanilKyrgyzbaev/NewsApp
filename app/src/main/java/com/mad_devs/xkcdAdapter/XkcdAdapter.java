package com.mad_devs.xkcdAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mad_devs.Interface.ItemClickListener;
import com.mad_devs.modelXkcd.Example;

public class XkcdAdapter extends RecyclerView.Adapter<XkcdViewHolder> {

    private Example example;
    private Context mContext;
    private LayoutInflater inflater;

    public XkcdAdapter(Example example , Context mContext ) {
        this.example = example;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public XkcdViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull XkcdViewHolder holder , int position) {
//        Glide.with (holder.itemView).load ( rssObject.feed.image ).into ( holder.imageView );
        Glide.with(holder.itemView).load (example.feed.image).into (holder.image_top);

        holder.setItemClickListener(new ItemClickListener () {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(example.getItems().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
