package com.danteh.hearthking.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import androidx.core.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danteh.hearthking.R;


public class DecksFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_decks, container, false);
        return v;
    }

}
