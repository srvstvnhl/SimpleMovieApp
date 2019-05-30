package com.example.nihal.simplemovieapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseComplete {

    @SerializedName("page")
    @Expose
    private Long page;

    @SerializedName("results")
    @Expose
    private ArrayList<ResponseListResult> results = new ArrayList<>();

    @SerializedName("total_pages")
    @Expose
    private Long total_pages;

    @SerializedName("total_results")
    @Expose
    private Long total_results;


    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public ArrayList<ResponseListResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResponseListResult> results) {
        this.results = results;
    }

    public Long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Long total_pages) {
        this.total_pages = total_pages;
    }

    public Long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Long total_results) {
        this.total_results = total_results;
    }
}
