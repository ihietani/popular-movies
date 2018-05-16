package com.ihietani.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihietani.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import static com.ihietani.popularmovies.MovieAdapter.IMAGE_BASE_URL;
import static com.ihietani.popularmovies.MovieAdapter.IMAGE_SIZE;

public class DetailActivity extends AppCompatActivity {


    public static final String EXTRA_CONTENT = "extra_content";
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    private TextView originalTitleTextView;
    private TextView overViewTextView;
    private TextView voteAverageTextView;
    private TextView releaseDateTextView;
    private ImageView moviePosterImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        originalTitleTextView = findViewById(R.id.movieTitle);
        overViewTextView = findViewById(R.id.movieOverview);
        voteAverageTextView = findViewById(R.id.movieUserRating);
        releaseDateTextView = findViewById(R.id.movieReleaseDate);
        moviePosterImageView = findViewById(R.id.moviePoster);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_CONTENT);
        if (movie != null){
            populateUI(movie);
            Picasso.get().load(IMAGE_BASE_URL
                    + IMAGE_SIZE + movie.getPosterPath()).into(moviePosterImageView);

        }
    }

    private void populateUI(Movie movie){
        originalTitleTextView.setText(movie.getTitle());
        overViewTextView.setText(movie.getOverview());
        voteAverageTextView.setText(Double.toString(movie.getVoteAverage()));
        releaseDateTextView.setText(movie.getReleaseDate().toString());

    }
}
