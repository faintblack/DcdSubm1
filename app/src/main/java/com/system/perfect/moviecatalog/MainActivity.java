package com.system.perfect.moviecatalog;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final String API = "a9f9c29a163472817de4426b1c8f62c7";
    String url = "https://api.themoviedb.org/3/discover/movie?api_key={" + API + "}&sort_by=popularity.desc";

    private final static String url2 = "http://www.mocky.io/v2/5c3e81473500005a003e98c3";

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

        getLoaderManager().initLoader(0, bundle, (android.app.LoaderManager.LoaderCallbacks<Object>) this);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_film = strCari.getText().toString().trim();

            }
        });
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, @Nullable Bundle bundle) {
        String judulFilm = bundle.getString(EXTRA_FILM);

        proBar.setVisibility(View.VISIBLE);
        if (proBar.getVisibility() == View.VISIBLE){
            lvMovie.setVisibility(View.GONE);
        }
        return new MovieAsynctaskLoader(this, bundle.getString(judulFilm));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {

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
