package com.example.hymnalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.ViewHolder> {

    List<String[]> songs;
    SongRecyclerPosition mSongRecyclerPosition;

    SongsRecyclerAdapter(Context context, SongRecyclerPosition songRecyclerPosition){
        try {
            InputStream is = context.getAssets().open("songs.csv");
            InputStreamReader reader = new InputStreamReader(is);
            songs = new CSVReader(reader).readAll();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        mSongRecyclerPosition = songRecyclerPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleAndNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mSongRecyclerPosition != null){
                        int position = getAdapterPosition();
                        if(getAdapterPosition() != RecyclerView.NO_POSITION){
                            mSongRecyclerPosition.setClickPosition(position);
                        }
                    }
                }
            });
            titleAndNumber = itemView.findViewById(R.id.songNumberAndTitle);
        }

        public void setData(String songNumber, String title) {
            titleAndNumber.setText(songNumber + " - " + title);
        }
    }
    @NonNull
    @Override
    public SongsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsRecyclerAdapter.ViewHolder holder, int position) {
        String songNumber = songs.get(position)[0];
        String title = songs.get(position)[1];

        holder.setData(songNumber, title);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
