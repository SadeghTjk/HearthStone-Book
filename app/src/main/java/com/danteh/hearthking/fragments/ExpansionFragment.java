package com.danteh.hearthking.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.danteh.hearthking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpansionFragment extends Fragment {


    public ExpansionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expansion, container, false);
    }

}
