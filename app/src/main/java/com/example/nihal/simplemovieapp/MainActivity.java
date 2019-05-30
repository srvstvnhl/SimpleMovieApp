package com.example.nihal.simplemovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nihal.simplemovieapp.Adapter.MovieRecyclerViewAdapter;
import com.example.nihal.simplemovieapp.Models.Movie;
import com.example.nihal.simplemovieapp.pojo.ResponseComplete;
import com.example.nihal.simplemovieapp.pojo.ResponseListResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements onItemClickListener{


    private RecyclerView recyclerView;
    private ArrayList<Movie> popularMovieArrayList=new ArrayList<>();

  //  private ArrayList<Movie> moviesId = new ArrayList<>();
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popularMovieArrayList = new ArrayList<>();

        initView();
    }

    public void initView(){

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadJson();

    }

    public void loadJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MoviesApi request = retrofit.create(MoviesApi.class);
        Call<ResponseComplete> completeCall = request.getPopMovieList("popular","06ecb6d94e4cecebfce417e34d9995e1");


        completeCall.enqueue(new Callback<ResponseComplete>() {
            @Override
            public void onResponse(Call<ResponseComplete> call, Response<ResponseComplete> response) {

                if(response.isSuccessful()){
                    popularMovieArrayList.addAll(setMovieList(response));


                    movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(popularMovieArrayList,MainActivity.this);
                    recyclerView.setAdapter(movieRecyclerViewAdapter);
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseComplete> call, Throwable t) {

                Log.e("TAG", "onFailure: "+t.getMessage());
            }
        });

    }

    public List<Movie> setMovieList(retrofit2.Response<ResponseComplete> response){

        List<Movie> movieArrayList = new ArrayList<>();

        for(ResponseListResult allMovieList : response.body().getResults()){

            Movie movie = new Movie();

            movie.setId(allMovieList.getId());
            movie.setBackdrop_path(allMovieList.getBackdropPath());
            movie.setOriginal_title(allMovieList.getOriginalTitle());
            movie.setPosterPath(allMovieList.getPosterPath());
            movie.setRelease_date(allMovieList.getReleaseDate());
            movie.setTitle(allMovieList.getTitle());
            movie.setVote_count(allMovieList.getVoteCount());


            movie.setGenre_ids(allMovieList.getGenreIDs());
            movieArrayList.add(movie);

        }
        return movieArrayList;
    }


    @Override
    public void onItemClick(int position,View view) {
        String id = popularMovieArrayList.get(position).getId();
        //String id = new Movie().getId();

        Intent intent = new Intent(MainActivity.this,DetailActivity.class);

        intent.putExtra("ID",id);
        startActivity(intent);

    }
}
