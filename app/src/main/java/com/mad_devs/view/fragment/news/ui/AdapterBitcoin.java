package com.mad_devs.view.fragment.news.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mad_devs.OnItemClickListener;
import com.mad_devs.Utils;
import com.mad_devs.data.model_Bitcoin.Article;
import com.mad_devs.internet.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBitcoin extends RecyclerView.Adapter<AdapterBitcoin.BitcoinViewHolder> {
    private ArrayList<Article> arrayList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;




    @NonNull
    @Override
    public BitcoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        context = parent.getContext ();
        View view = LayoutInflater.from(parent.getContext ()).inflate( R.layout.news_item , parent,false);
        return new BitcoinViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull BitcoinViewHolder holder , int position) {
        holder.onBind ( arrayList.get ( position ), context );
    }

    @Override
    public int getItemCount() {
        return arrayList.size ();
    }

    public void initListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void update(List<Article> articles){
        arrayList.clear();
        arrayList.addAll(articles);
        notifyDataSetChanged ();
    }

    public class BitcoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView desc;
        private TextView author;
        private TextView published_ad;
        private TextView source;
        private TextView time;
        private ImageView imageView;
        private ProgressBar progressBar;

        Context context;

        public BitcoinViewHolder(@NonNull View itemView) {
            super ( itemView );
            title = itemView.findViewById( R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);

            itemView.setOnClickListener ( v -> onItemClickListener.onItemClickListener( v , getAdapterPosition()) );

        }

        @SuppressLint("CheckResult")
        public void onBind(Article article, Context context) {
            this.context = context;
            title.setText ( article.getTitle ());
            desc.setText ( article.getDescription ());
            author.setText ( article.getAuthor () );
            published_ad.setText (article.getPublishedAt () );
            source.setText ( article.getSource ().getName ());
            time.setText ( "\u2022" + Utils.DateToTimeFormat ( Utils.getCountry () ) );

            if (context != null) {
                Glide.with(context)
                        .load ( article.getUrlToImage () )
                        .into ( imageView );
            }

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClickListener(v, getAdapterPosition());


            //        getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentSightDetailed() ).commit();
            //        Toast.makeText(itemView.getContext(), "Item clicked. "+textViewSightsName.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}


