package com.example.moviesapp.api;

import com.example.moviesapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apikey);

}
