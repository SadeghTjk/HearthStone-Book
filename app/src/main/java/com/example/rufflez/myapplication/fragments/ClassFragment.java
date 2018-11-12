package com.example.rufflez.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rufflez.myapplication.Cards;
import com.example.rufflez.myapplication.R;
import com.example.rufflez.myapplication.adapter.CGLM;
import com.example.rufflez.myapplication.adapter.CardsListAdapter;
import com.example.rufflez.myapplication.readJson;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment {
    List<Cards> priestCardslist;
    RecyclerView priestRV;
    Context context;
    List<Cards> priestCards = new ArrayList<>();
    com.example.rufflez.myapplication.readJson readJson = new readJson(context);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class, container, false);
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
                    priestRV.setAdapter(new CardsListAdapter(priestCards, context));
            }
        }, 4000);


        return rootView;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new AboutFragment(), "Floating Labels");
        adapter.addFrag(new ClassFragment(), "Priest");
        adapter.addFrag(new CardsFragment(), "Coordinator Layout");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
