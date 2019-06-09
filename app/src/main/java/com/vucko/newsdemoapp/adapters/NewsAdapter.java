package com.vucko.newsdemoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vucko.newsdemoapp.NewsActivity;
import com.vucko.newsdemoapp.R;
import com.vucko.newsdemoapp.api.models.NewsArticle;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<NewsArticle> newsArticles;

    public NewsAdapter(Context context, List<NewsArticle> newsArticles) {
        this.context = context;
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.newsImageView)
        ImageView newsImageView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.descriptionTextView)
        TextView descriptionTextView;
        @BindView(R.id.container)
        ConstraintLayout container;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(NewsArticle newsArticle) {
            titleTextView.setText(newsArticle.getTitle());
            descriptionTextView.setText(newsArticle.getDescription());
            Picasso.get().load(newsArticle.getUrlToImage()).into(newsImageView);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsActivity.start(context, newsArticle);
                }
            });
        }
    }
}
