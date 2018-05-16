package com.ihietani.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ihietani.popularmovies.model.Results;
import com.ihietani.popularmovies.utils.JsonUtils;
import com.ihietani.popularmovies.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeMovieDatabaseQuery();
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_settings){

        }
        return super.onOptionsItemSelected(item);
    }
    */

    private void makeMovieDatabaseQuery() {;
        URL movieDatabaseUrl = NetworkUtils.buildUrl(NetworkUtils.Endpoint.POPULAR);
        new MovieDatabaseQueryTask().execute(movieDatabaseUrl);
    }

    private void launchDetailActivity(int position, Results results){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_CONTENT, results.getMovies().get(position));
        startActivity(intent);
    }

    private void showMovies(String s){
        final Results results = JsonUtils.parseMovieJson(s);
        MovieAdapter adapter = new MovieAdapter(this, results.getMovies());
        gridView = (GridView) findViewById(R.id.movies_griedview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchDetailActivity(position, results);
            }
        });
    }

    public class MovieDatabaseQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            Log.d(LOG_TAG, s);
            if(s != null && s.length() > 0){
                showMovies(s);
            }
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL queryUrl = urls[0];
            String movieDatabaseResult = null;
            try{
                movieDatabaseResult = NetworkUtils.getResponseFromHttpUrl(queryUrl);
                Log.d(LOG_TAG, movieDatabaseResult);
            }catch (IOException e){
                e.printStackTrace();
            }
            return movieDatabaseResult;
        }
    }
}
