package com.system.perfect.moviecatalog;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieItems implements Parcelable {
    private int id;
    private String judul;
    private String genre;
    private String sinopsis;
    private String rilis;
    private String dewasa;
    private String poster;
    private String vote;
    private String bahasa_asli;

    public MovieItems(JSONObject obj){
        try{
            // Get data from JSON
            int id = obj.getInt("id");
            String judul = obj.getString("title");
            String vote = obj.getString("vote_average");
            String poster = obj.getString("poster_path");
            JSONArray genre_ids = obj.getJSONArray("genre_ids");
            String genre = genre_ids.toString();
            String bahasa_asli = obj.getString("original_language");
            String rilis = obj.getString("release_date");
            String sinopsis = obj.getString("overview");
            String dewasa = obj.getString("adult");

            // Set data to attribute
            this.id = id;
            this.judul = judul;
            this.genre = genre;
            this.sinopsis = sinopsis;
            this.rilis = rilis;
            this.dewasa = dewasa;
            this.poster = poster;
            this.vote = vote;
            this.bahasa_asli = bahasa_asli;

        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getDewasa() {
        return dewasa;
    }

    public void setDewasa(String dewasa) {
        this.dewasa = dewasa;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getBahasa_asli() {
        return bahasa_asli;
    }

    public void setBahasa_asli(String bahasa_asli) {
        this.bahasa_asli = bahasa_asli;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dewasa);
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.genre);
        dest.writeString(this.sinopsis);
        dest.writeString(this.rilis);
        dest.writeString(this.poster);
        dest.writeString(this.vote);
        dest.writeString(this.bahasa_asli);
    }

    protected MovieItems(Parcel in) {
        this.dewasa = in.readString();
        this.id = in.readInt();
        this.judul = in.readString();
        this.genre = in.readString();
        this.sinopsis = in.readString();
        this.rilis = in.readString();
        this.poster = in.readString();
        this.vote = in.readString();
        this.bahasa_asli = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}
