package com.system.perfect.moviecatalog;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    private static final String TAG = MainActivity.class.getName();
    ImageView posterImage, backgroundImage;
    ListView lvMovie;
    Button btnCari;
    EditText strCari;
    TextView tvJudul, tvDeskripsi, tvRilis;
    ProgressBar proBar;
    MoviesAdapter adapter;

    final static String EXTRA_FILM = "EXTRA_FILM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MoviesAdapter(this);
        adapter.notifyDataSetChanged();

        lvMovie = (ListView)findViewById(R.id.list_film);
        lvMovie.setAdapter(adapter);

        btnCari = (Button)findViewById(R.id.btn_cari);
        strCari = (EditText)findViewById(R.id.str_search);
        proBar = (ProgressBar)findViewById(R.id.pro_bar);

        String judulFilm = strCari.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FILM, judulFilm);

        getSupportLoaderManager().initLoader(0, bundle,  this);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judulFilm = strCari.getText().toString();
                if (TextUtils.isEmpty(judulFilm)){
                    return;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRA_FILM, judulFilm);
                    getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
                }

            }
        });
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, @Nullable Bundle bundle) {
        String judulFilm = "";

        if (bundle != null){
            judulFilm = bundle.getString(EXTRA_FILM);
        }

        proBar.setVisibility(View.VISIBLE);
        if (proBar.getVisibility() == View.VISIBLE){
            lvMovie.setVisibility(View.GONE);
        }
        return new MovieAsynctaskLoader(this, judulFilm);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        adapter.setData(movieItems);
        proBar.setVisibility(View.GONE);
        if (proBar.getVisibility() == View.GONE){
            lvMovie.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }



    /*
    private void requestFilm() {
        // Inisiasi daftar request
        mRequest = Volley.newRequestQueue(this);

        // Inisiasi string request
        sRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error : " + error.toString());
            }
        });

        // Add String request
        mRequest.add(sRequest);
    }
    */
}
