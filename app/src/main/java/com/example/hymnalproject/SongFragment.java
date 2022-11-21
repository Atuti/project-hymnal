package com.example.hymnalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.hymnalproject.databinding.SongViewBinding;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SongFragment extends Fragment {

    private SongViewBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SongViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = binding.textSongView;

        int position = getArguments().getInt("position");

        List<String[]> songs = null;

        try {
            InputStream is = getActivity().getAssets().open("songs.csv");
            InputStreamReader reader = new InputStreamReader(is);
            songs = new CSVReader(reader).readAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        String stanzas = songs.get(position)[2];
        String choruses = songs.get(position)[3];

        textView.setText(songs.get(position)[2] + "\n" + songs.get(position)[3]);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}