package com.example.rufflez.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rufflez.myapplication.adapter.CGLM;
import com.example.rufflez.myapplication.adapter.CardsListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rufflez on 6/20/15.
 */
public class FABLayoutFragment extends Fragment {
    List<Cards> priestCardslist;
    RecyclerView priestRV;
    Context context;
    List<Cards> priestCards = new ArrayList<>();
    readJson readJson = new readJson(context);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floating_action_button, container, false);
        context = getActivity();
        priestRV = rootView.findViewById(R.id.priestRV);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    priestCardslist = readJson.getcardsList();
                    Log.e("priestCardslist size", ": " + priestCardslist.size());


                    for (int i = 0; i < priestCardslist.size(); i++)
                        if (priestCardslist.get(i).getCardClass().equals("PRIEST")) {
                            Log.e("a card", ": " + priestCards.size());
                            priestCards.add(priestCardslist.get(i));
                        }

                    priestRV.setLayoutManager(new CGLM(context, 3, GridLayoutManager.VERTICAL, false));
                    priestRV.setAdapter(new CardsListAdapter(priestCards, context,priestRV));
            }
        }, 4000);


        return rootView;
    }
}
