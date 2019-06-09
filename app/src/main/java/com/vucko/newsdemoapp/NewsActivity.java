package com.vucko.newsdemoapp;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vucko.newsdemoapp.api.models.NewsArticle;
import com.vucko.newsdemoapp.utils.Constants;

import butterknife.BindView;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.newsImageView)
    ImageView newsImageView;
    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.contentTextView)
    TextView contentTextView;

    public static void start(Context context, NewsArticle newsArticle) {
        Intent starter = new Intent(context, NewsActivity.class);
        starter.putExtra(Constants.NEWS, newsArticle);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news;
    }

    @Override
    protected void setupUI() {
        setToolbarBackArrow(true);
        if(getIntent() != null){
            NewsArticle newsArticle = (NewsArticle) getIntent().getSerializableExtra(Constants.NEWS);
            if (newsArticle != null) {
                titleTextView.setText(newsArticle.getTitle());
                descriptionTextView.setText(newsArticle.getDescription());
                contentTextView.setText(newsArticle.getContent());
                Picasso.get().load(newsArticle.getUrlToImage()).into(newsImageView);
            }
        }
    }
}
