package com.example.nihal.simplemovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihal.simplemovieapp.Models.Movie;
import com.example.nihal.simplemovieapp.pojo.ResponseGenreIDs;
import com.example.nihal.simplemovieapp.pojo.ResponseSingleResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    ImageView imgDetail;
    TextView tvDetailName,tvDetailReleaseDate,tvDetailVoteAvg,tvDetailPopularity,tvDetailOverview;
    Button btnBack;

    private Movie movieDetail;
    Movie currentMovie;

    String movieId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_detail_activity);
      //  Intent intent = getIntent();
       // movieId = getIntent().getStringExtra("ID");
        if(getIntent().hasExtra("ID"))
        {
            movieId = getIntent().getStringExtra("ID");
            Log.e("TAG",""+movieId);
        }


        imgDetail = findViewById(R.id.imgDetail);
        tvDetailName=findViewById(R.id.tvDetailName);
        tvDetailReleaseDate = findViewById(R.id.tvDetailReleaseDate);
        tvDetailVoteAvg=findViewById(R.id.tvDetailVoteAverage);
        tvDetailPopularity=findViewById(R.id.tvDetailPopularity);
        tvDetailOverview=findViewById(R.id.tvDetailOverview);
        btnBack=findViewById(R.id.backButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        fetchDetailMovies();

    }

    public void fetchDetailMovies(){

        Log.e("TAG","insideFetch");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesApi requestDetail = retrofit.create(MoviesApi.class);

        Log.e("TAG",movieId);
        Call<ResponseSingleResults> responseSingleResultsCall =requestDetail.getSingleMovieDetail(movieId);
        responseSingleResultsCall.enqueue(new Callback<ResponseSingleResults>() {
            @Override
            public void onResponse(Call<ResponseSingleResults> call, Response<ResponseSingleResults> response) {


                if(response.isSuccessful()){
                    Log.e("TAG","Vote"+response.body().getVote_count());
                    List<String> genreIDsList = new ArrayList<>();

                    for(ResponseGenreIDs genreIDs : response.body().getGenreIDs()){
                        genreIDsList.add(genreIDs.getName());
                    }

                    currentMovie = new Movie(response.body().getPosterPath()
                            , response.body().getOverView(), response.body().getReleaseDate()
                            , genreIDsList, response.body().getId().toString()
                            , response.body().getOriginalTitle(), response.body().getBackdropPath()
                            , response.body().getPopularity(), response.body().getVote_count());

                    Log.e("TAG","Title"+ currentMovie.getTitle());
                    setMoviesDetails();
                }else{
                   Log.e("TAG","fail");
                }

            }

            @Override
            public void onFailure(Call<ResponseSingleResults> call, Throwable t) {

                Toast.makeText(DetailActivity.this, "please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setMoviesDetails(){


        tvDetailName.setText(currentMovie.getTitle());
        tvDetailOverview.setText(currentMovie.getOverview());
        tvDetailVoteAvg.setText(currentMovie.getVote_count());
        tvDetailPopularity.setText(currentMovie.getPopularity());
        tvDetailReleaseDate.setText(currentMovie.getRelease_date());

        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w342"+currentMovie.getPosterPath()).into(imgDetail);

    }

}
