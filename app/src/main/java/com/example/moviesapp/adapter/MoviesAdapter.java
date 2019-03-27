package com.example.moviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviesapp.DetailActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContxt;
    private List<Movie> movieList;

    public  MoviesAdapter(Context mContxt, List<Movie> movieList){
        this.mContxt = mContxt;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(movieList.get(i).getOrigionalTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.userrating.setText(vote);

        Glide.with(mContxt)
                .load(movieList.get(i).getPosterPath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContxt, DetailActivity.class);
                        intent.putExtra("origional_title", movieList.get(pos).getOrigionalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", movieList.get(pos).getVoteAverage());
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContxt.startActivity(intent);
                        Toast.makeText(v.getContext(), "You Clicked" + clickedDataItem.getOrigionalTitle(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
