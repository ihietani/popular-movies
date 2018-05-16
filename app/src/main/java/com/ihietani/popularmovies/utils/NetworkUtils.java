package com.ihietani.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public enum Endpoint{
        POPULAR ("popular"),
        TOP_RATED ("top_rated");

        private String endpoint;

        Endpoint(String ep){
            endpoint = ep;
        }

        public String getEndpoint(){
            return endpoint;
        }
    }

    public static final String API_KEY = "";
    public static final String SECURE_BASE_URL = "https://api.themoviedb.org/3/movie";
    public static final String AUTH = "api_key";

    public static URL buildUrl(Endpoint ep){
        Uri builtUri = Uri.parse(SECURE_BASE_URL).buildUpon()
                .appendPath(ep.getEndpoint())
                .appendQueryParameter(AUTH, API_KEY)
                .build();
        Log.d(LOG_TAG, builtUri.toString());
        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
