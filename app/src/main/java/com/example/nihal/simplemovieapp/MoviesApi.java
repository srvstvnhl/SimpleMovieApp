package com.example.nihal.simplemovieapp;

import android.util.Log;

import com.example.nihal.simplemovieapp.pojo.ResponseComplete;
import com.example.nihal.simplemovieapp.pojo.ResponseSingleResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("{type}")
    Call<ResponseComplete> getPopMovieList(@Path("type") String type, @Query("api_key") String api_key);

    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=06ecb6d94e4cecebfce417e34d9995e1")
    Call<ResponseSingleResults> getSingleMovieDetail(@Path("movie_id") String movieId);

}
