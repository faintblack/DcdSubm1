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

    // Memasukkan data film kedalam ArrayList
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

    @Override
    public int getCount() {
        if (mData == null){
            return 0;
        } else {
            return mData.size();
        }
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
        ViewHolder hold = null;
        if (convertView == null){
            hold = new ViewHolder();
            convertView = mInflater.inflate(R.layout.daftar_film, null);
            hold.textJudul = (TextView)convertView.findViewById(R.id.tvJudul);
            hold.textDeskripsi = (TextView)convertView.findViewById(R.id.tvDeskripsi);
            hold.textRilis = (TextView)convertView.findViewById(R.id.tvRilis);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolder)convertView.getTag();
        }
        hold.textJudul.setText(mData.get(position).getJudul());
        hold.textDeskripsi.setText(mData.get(position).getDeskripsi());
        hold.textRilis.setText(mData.get(position).getRilis());
        return convertView;
    }

    private static class ViewHolder{
        TextView textJudul;
        TextView textDeskripsi;
        TextView textRilis;
    }
}
