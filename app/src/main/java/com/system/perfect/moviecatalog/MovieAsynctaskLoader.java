package com.system.perfect.moviecatalog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.SyncHttpClient;

import java.util.ArrayList;

public class MovieAsynctaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> mData ;
    private boolean mResult = false;
    private String kumpulanFilm;

    public MovieAsynctaskLoader(@NonNull final Context context, String kumpulanFilm) {
        super(context);
        onContentChanged();
        this.kumpulanFilm = kumpulanFilm;
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

    private static final String API = "a9f9c29a163472817de4426b1c8f62c7";

    @Nullable
    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient netClient = new SyncHttpClient();

        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key={" + API + "}&language=en-US&query={" + "one" + "}";;
        return null;
    }

}
