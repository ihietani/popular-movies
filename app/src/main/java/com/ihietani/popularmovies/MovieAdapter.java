package com.ihietani.popularmovies;

import com.ihietani.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private static String LOG_TAG = MovieAdapter.class.getSimpleName();

    public static final String IMAGE_SIZE = "w185/";
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    public MovieAdapter(Context context, List<Movie> movies)
    {
        super(context, 0, movies);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Movie movie = getItem(position);

        //if a new View object, inflate the layout
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.get().load(IMAGE_BASE_URL
                + IMAGE_SIZE + movie.getPosterPath()).into(iconView);

        return convertView;
    }
}
