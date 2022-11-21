package com.example.hymnalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hymnalproject.databinding.SongsViewRecyclerBinding;

public class SongsFragment extends Fragment implements SongRecyclerPosition{

    private SongsViewRecyclerBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SongsViewRecyclerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView songsRecycler = binding.songsRecycler;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        SongsRecyclerAdapter songsAdapter = new SongsRecyclerAdapter(getActivity(), this);
        songsRecycler.setLayoutManager(linearLayoutManager);
        songsRecycler.setAdapter(songsAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setClickPosition(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        NavHostFragment.findNavController(this).navigate(
                R.id.action_SongsFragment_to_SongFragment, bundle
        );
    }
}