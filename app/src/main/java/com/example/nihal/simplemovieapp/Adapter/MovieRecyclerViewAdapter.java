package com.example.nihal.simplemovieapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nihal.simplemovieapp.Constants;
import com.example.nihal.simplemovieapp.Models.Movie;
import com.example.nihal.simplemovieapp.R;
import com.example.nihal.simplemovieapp.onItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.PopulateMovieHolder> {


    private ArrayList<Movie> movieArrayList;
    private Context context;
    onItemClickListener onItemClickListener1;

    public MovieRecyclerViewAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
        onItemClickListener1 = (onItemClickListener) context;
    }

    @NonNull
    @Override
    public PopulateMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_row_item,viewGroup,false);

        return new PopulateMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopulateMovieHolder populateMovieHolder, final int i) {

        Movie currentItem = movieArrayList.get(i);
        populateMovieHolder.tvTitle.setText(currentItem.getTitle());
        populateMovieHolder.tvOriginalTitle.setText(currentItem.getOriginal_title());
        populateMovieHolder.tvReleaseDate.setText(currentItem.getRelease_date());

        StringBuilder builder = new StringBuilder();
        builder.append(Constants.IMAGE_PATH_PREFIX).append(currentItem.getPosterPath());


        Picasso.with(context).load(builder.toString()).into(populateMovieHolder.img);
        populateMovieHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener1.onItemClick(i, v);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class PopulateMovieHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView tvTitle,tvOriginalTitle,tvReleaseDate;
        ImageView img;
        public PopulateMovieHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            img = itemView.findViewById(R.id.img);
            tvTitle =itemView.findViewById(R.id.tvTitle);
            tvOriginalTitle = itemView.findViewById(R.id.tvOriginalTitle);
            tvReleaseDate=itemView.findViewById(R.id.tvReleaseDate);

        }
    }

}
