package com.ihietani.popularmovies.utils;


import com.google.gson.Gson;
import com.ihietani.popularmovies.model.Results;

public class JsonUtils {

    public static Results parseMovieJson(String json){
        Gson gson = new Gson();
        Results movie = gson.fromJson(json, Results.class);
        return movie;
    }
}
