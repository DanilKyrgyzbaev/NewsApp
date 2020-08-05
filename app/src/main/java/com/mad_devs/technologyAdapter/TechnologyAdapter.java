package com.mad_devs.technologyAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mad_devs.Interface.ItemClickListener;
import com.mad_devs.modelTechnology.Example;
import com.mad_devs.newsapp.R;


class TechnologyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public TextView txtTitleTop;
    public ImageView imageTop;
    private ItemClickListener itemClickListenerX;


    public TechnologyViewHolder(@NonNull View itemView) {
        super ( itemView );
        txtTitleTop = itemView.findViewById( R.id.txtTitleTop);
        imageTop = itemView.findViewById(R.id.imageTop);

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

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyViewHolder> {

    private Example example;
    private Context mContext;
    private LayoutInflater inflater;

    public TechnologyAdapter(Example example , Context mContext ) {
        this.example = example;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public TechnologyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View itemView = inflater.inflate( R.layout.recyclerview_top, parent, false);
        return new TechnologyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull TechnologyViewHolder holder , int position) {
       // Glide.with(holder.itemView).load (example.feed.image).into (holder.image_top);
        Glide.with ( holder.itemView ).load ( example.feed.image).into ( holder.imageTop );
        holder.txtTitleTop.setText ( example.getItems ().get ( position).getTitle ());

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
        return example.items.size ();
    }

}

