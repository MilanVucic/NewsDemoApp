package com.vucko.newsdemoapp;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
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
    private NewsArticle newsArticle;
    private MenuItem item;

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
            newsArticle = (NewsArticle) getIntent().getSerializableExtra(Constants.NEWS);
            if (newsArticle != null) {
                setToolbarTitle(newsArticle.getTitle());
                titleTextView.setText(newsArticle.getTitle());
                descriptionTextView.setText(newsArticle.getDescription());
                contentTextView.setText(newsArticle.getContent());
                Picasso.get().load(newsArticle.getUrlToImage()).into(newsImageView);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_activity_menu, menu);
        item = menu.findItem(R.id.favourite);
        setFavouriteIcon();
        return true;
    }

    private void setFavouriteIcon() {
        if(newsArticle != null){
            if(newsArticle.isFavourite()){
                item.setIcon(android.R.drawable.btn_star_big_on);
            } else {
                item.setIcon(android.R.drawable.btn_star_big_off);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.favourite){
            toggleFavourite();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleFavourite() {
        newsArticle.setFavourite(!newsArticle.isFavourite());
        setFavouriteIcon();
        newsArticle.save();
    }
}
