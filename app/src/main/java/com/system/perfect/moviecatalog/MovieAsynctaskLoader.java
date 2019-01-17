package com.system.perfect.moviecatalog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieAsynctaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> mData ;
    private boolean mResult = false;
    private static final String API = "a9f9c29a163472817de4426b1c8f62c7";

    private String judulFilm;

    public MovieAsynctaskLoader(@NonNull final Context context, String judulFilm) {
        super(context);
        onContentChanged();
        this.judulFilm = judulFilm;
    }

    protected void onStartLoading(){
        if (takeContentChanged()){
            forceLoad();
        } else if (mResult){
            deliverResult(mData);
        }
    }

    public void deliverResult(final ArrayList<MovieItems> data){
        mData = data;
        mResult = true;
        super.deliverResult(data);
    }

    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mResult){
            onReleaseResource(mData);
            mData = null;
            mResult = false;
        }
    }

    protected void onReleaseResource(ArrayList<MovieItems> dataSumber) {
    }

    @Nullable
    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient netClient = new SyncHttpClient();

        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API + "&sort_by=popularity.desc";
        String urlCari = "https://api.themoviedb.org/3/search/movie?api_key=" + API + "&language=en-US&query=" + judulFilm;;
        String fixUrl = "";

        if (TextUtils.isEmpty(judulFilm)){
            fixUrl = url;
        } else {
            fixUrl = urlCari;
        }

        netClient.get(fixUrl, new AsyncHttpResponseHandler() {
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Mengambil Data Film : ", "onSuccess : Berhasil");
                try{
                    JSONObject responseObject = new JSONObject(new String(responseBody));
                    JSONArray hasil = responseObject.getJSONArray("result");

                    for (int i = 0; i < hasil.length(); i++){
                        JSONObject film = hasil.getJSONObject(i);
                        /*
                        String id = film.getString("id");
                        String judul = film.getString("title");
                        String vote = film.getString("vote_average");
                        String poster = film.getString("poster_path");
                        JSONArray genre_ids = film.getJSONArray("genre_ids");
                        String genre = genre_ids.toString();
                        String bahasa_asli = film.getString("original_language");
                        String rilis = film.getString("release_date");
                        String sinopsis = film.getString("overview");
                        String dewasa = film.getString("adult");
                        */

                        // Memasukkan data setiap film kedalam class MovieItems
                        MovieItems items = new MovieItems(film);
                        movieItemses.add(items);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }

}
