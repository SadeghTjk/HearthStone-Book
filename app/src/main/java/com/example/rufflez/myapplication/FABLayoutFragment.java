package com.example.rufflez.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rufflez.myapplication.adapter.CardsListAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rufflez on 6/20/15.
 */
public class FABLayoutFragment extends Fragment {
    List<Cards> cardsList;
    RecyclerView priestRV;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floating_action_button, container, false);
        Context context = getActivity();
//        priestRV = rootView.findViewById(R.id.priestRV);
//        readJson rj = new readJson(context);
//        cardsList = rj.getPriestCards();
//        Toast.makeText(context, "priest: "+cardsList.size(), Toast.LENGTH_SHORT).show();
//        priestRV.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
//        priestRV.setAdapter(new CardsListAdapter(cardsList,context));

        return rootView;
    }
}
