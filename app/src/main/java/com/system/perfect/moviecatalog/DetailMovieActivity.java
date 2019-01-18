package com.system.perfect.moviecatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "EXTRA_FILM";
    TextView textJudul, textGenre, textRilis, textRating, textSinopsis;
    ImageView poster, posterKecil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        textJudul = findViewById(R.id.tvDetailJudul);
        //textGenre = findViewById(R.id.tvGenre);
        textRilis = findViewById(R.id.tvDetailRilis);
        textRating = findViewById(R.id.tvDetailRating);
        textSinopsis = findViewById(R.id.tvDetailSinopsis);
        poster = findViewById(R.id.poster);
        posterKecil = findViewById(R.id.posterSmall);

        MovieItems movie = getIntent().getParcelableExtra(EXTRA_FILM);

        getSupportActionBar().setTitle(movie.getJudul());

        Glide.with(this).load("http://image.tmdb.org/t/p/original/" + movie.getPoster()).into(poster);
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + movie.getPoster()).into(posterKecil);
        textJudul.setText(movie.getJudul());
        // textGenre.setText(movie.getGenre());
        // textRilis.setText(movie.getRilis());
        textRating.setText(movie.getVote());
        textSinopsis.setText(movie.getSinopsis());

        String rilis = movie.getRilis();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date tgl = formatTanggal.parse(rilis);
            SimpleDateFormat formatTglBaru = new SimpleDateFormat("dd MMM yyyy");
            String tglRilis = formatTglBaru.format(tgl);
            textRilis.setText(tglRilis);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
