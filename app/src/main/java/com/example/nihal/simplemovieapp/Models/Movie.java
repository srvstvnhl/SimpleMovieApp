package com.example.nihal.simplemovieapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    private String posterPath;
    private String overview;
    private String release_date;
    private List<String> genre_ids = new ArrayList<>();
    private String id;
    private String title;
    private String backdrop_path;
    private String popularity;
    private String vote_count;
    private String original_title;



    public Movie() {
    }

    public Movie(String posterPath, String overview, String release_date, List<String> genre_ids, String id, String title, String backdrop_path, String popularity, String vote_count, String original_title) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.original_title = original_title;
    }

    public Movie(String original_title) {
        this.original_title = original_title;
    }

    public Movie(String posterPath, String overview, String release_date,
                 List<String> genre_ids, String id, String title,
                 String backdrop_path, String popularity, String vote_count) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;

    }

    //read String to initialize value of strings
    public Movie(Parcel in){
        posterPath = in.readString();
        overview = in.readString();
        release_date = in.readString();
        genre_ids= in.createStringArrayList();
        id=in.readString();
        title=in.readString();
        backdrop_path=in.readString();
        popularity=in.readString();
        vote_count=in.readString();
        original_title=in.readString();
    }

    //


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    /*public ArrayList<String> getGenre_ids() {
        return genre_ids;
    }*/

    public List<String> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<String> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setGenre_ids(ArrayList<String> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //required method to write to parcel.
    //it is of as same order as the constructor of parcel that is created above.
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeStringList(genre_ids);
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeString(popularity);
        parcel.writeString(vote_count);
        parcel.writeString(original_title);
    }
}
