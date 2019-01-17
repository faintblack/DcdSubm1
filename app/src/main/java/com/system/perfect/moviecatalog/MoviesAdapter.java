package com.system.perfect.moviecatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MoviesAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MoviesAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Memasukkan data film kedalam ArrayList dari class MovieItems
    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null)return 0;
        return mData.size();

    }

    public int getItemViewType(int position){
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.daftar_film, null);
            holder.textJudul = (TextView)convertView.findViewById(R.id.tvJudul);
            holder.textSinopsis = (TextView)convertView.findViewById(R.id.tvDeskripsi);
            holder.textRilis = (TextView)convertView.findViewById(R.id.tvRilis);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textJudul.setText(mData.get(position).getJudul());
        holder.textSinopsis.setText(mData.get(position).getSinopsis());
        holder.textRilis.setText(mData.get(position).getRilis());
        return convertView;
    }

    private static class ViewHolder{
        TextView textJudul;
        TextView textSinopsis;
        TextView textRilis;
    }
}
