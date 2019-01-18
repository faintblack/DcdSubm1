package com.system.perfect.moviecatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "EXTRA_FILM";
    TextView textJudul, textBahasa, textRilis, textRating, textSinopsis;
    ImageView poster, posterKecil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        textJudul = findViewById(R.id.tvDetailJudul);
        // textBahasa = findViewById(R.id.tvDetailBahasa);
        textRilis = findViewById(R.id.tvDetailRilis);
        textRating = findViewById(R.id.tvDetailRating);
        textSinopsis = findViewById(R.id.tvDetailSinopsis);
        poster = findViewById(R.id.poster);
        posterKecil = findViewById(R.id.posterSmall);

        MovieItems movie = getIntent().getParcelableExtra(EXTRA_FILM);

        Glide.with(this).load("http://image.tmdb.org/t/p/original/" + movie.getPoster()).into(poster);
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + movie.getPoster()).into(posterKecil);
        textJudul.setText(movie.getJudul());
        // textBahasa.setText(movie.getBahasa_asli());
        textRilis.setText(movie.getRilis());
        textRating.setText(movie.getVote());
        textSinopsis.setText(movie.getSinopsis());

    }
}
