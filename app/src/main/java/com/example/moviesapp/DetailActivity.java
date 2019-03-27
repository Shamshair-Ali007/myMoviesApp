package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = findViewById(R.id.thumbnail_image_header);
        nameOfMovie = findViewById(R.id.title);
        plotSynopsis = findViewById(R.id.plotsynopsis);
        userRating = findViewById(R.id.userrating);
        releaseDate = findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("origional_title")){
            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("origional_title");
            String synopsis = getIntent().getExtras().getString("overvew");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOdRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOdRelease);
        }else {

            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
    }
    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }
}
