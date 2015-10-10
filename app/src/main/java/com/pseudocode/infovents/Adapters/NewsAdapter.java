package com.pseudocode.infovents.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pseudocode.infovents.Classes.News;
import com.pseudocode.infovents.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Baymax on 10/10/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {
    private List<News> mDataset;
    private SparseBooleanArray selectedItems;
    private Context mContext;


    public NewsAdapter(Context context, List<News> myDataset) {
        mContext = context;
        mDataset = myDataset;
        selectedItems = new SparseBooleanArray();

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        News news = mDataset.get(position);
        Picasso.with(mContext).load(news.getNewsImage())
                .error(R.drawable.side_nav_bar)
                .placeholder(R.drawable.side_nav_bar)
                .into(holder.imageView);

        holder.textViewNews.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);

        holder.textViewNews.setTag(holder);
        holder.textViewDate.setTag(holder);
        holder.textViewOrg.setTag(holder);
        holder.textVieDesc.setTag(holder);
        holder.imageView.setTag(holder);

        //Setting text view title
        holder.textViewNews.setText(news.getNewsName());
        holder.textViewDate.setText(news.getNewsDatePosted());
        holder.textViewOrg.setText(news.getNewsOrgId());
        holder.textVieDesc.setText(news.getNewsDescription());
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNews;
        TextView textViewDate;
        TextView textViewOrg;
        TextView textVieDesc;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textViewNews = (TextView) view.findViewById(R.id.news);
            this.textViewDate = (TextView) view.findViewById(R.id.datePosted);
            this.textVieDesc = (TextView) view.findViewById(R.id.newsdesc);
            this.textViewOrg = (TextView) view.findViewById(R.id.posted);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getAdapterPosition();

            News news = mDataset.get(position);

        }
    };
}