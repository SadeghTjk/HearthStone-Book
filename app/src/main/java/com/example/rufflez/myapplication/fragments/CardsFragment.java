package com.example.rufflez.myapplication.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.rufflez.myapplication.Cards;
import com.example.rufflez.myapplication.MainActivity;
import com.example.rufflez.myapplication.R;
import com.example.rufflez.myapplication.adapter.CGLM;
import com.example.rufflez.myapplication.adapter.CardsListAdapter;
import com.example.rufflez.myapplication.readJson;

import java.util.ArrayList;
import java.util.List;

public class CardsFragment extends Fragment {
    RecyclerView rv;
    List<Cards> cardsList, empty;
    Context context;
    CardsListAdapter cla;
    private SearchView searchView = null;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    RadioGroup radio_class;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        readJson readJson = new readJson(context);
        cardsList = readJson.getcardsList();

        context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);
        searchView = rootView.findViewById(R.id.appbar_search);
        //viewPager = rootView.findViewById(R.id.viewpager);
        radio_class = rootView.findViewById(R.id.radio_class);
        empty = new ArrayList<>();
        rv = rootView.findViewById(R.id.rv);
        cla = new CardsListAdapter(cardsList, context);
        rv.setLayoutManager(new CGLM(context, 3, GridLayoutManager.HORIZONTAL, false));
        rv.setAdapter(cla);

        //adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        //setupViewPager(viewPager);


        radio_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.druid:
                        getCardsforClass("DRUID");
                        cla.notifyDataSetChanged();
                        break;

                    case R.id.priest:
                        getCardsforClass("PRIEST");
                        break;
                    case R.id.mage:
                        cla = new CardsListAdapter(cardsList, context);
                        rv.setLayoutManager(new CGLM(context, 3, GridLayoutManager.HORIZONTAL, false));

                        rv.setAdapter(cla);
                        break;
                }
            }
        });

//        recyclerView.setLayoutManager(new CGLM(context, 3, GridLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(cla);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.appbar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    cla.getFilter().filter(newText);
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    cla.getFilter().filter(query);
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.appbar_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
//        adapter.addFrag(new DruidCards(), "Druid Cards");
//        adapter.addFrag(new PriestCards(), "Priest Cards");
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

    public void getCardsforClass(String className) {
        final List<Cards> temp = new ArrayList<>();
        for (int i = 0; i < cardsList.size(); i++)
            if (cardsList.get(i).getCardClass().equals(className)) {
                Log.e("a card", ": " + temp.size());
                temp.add(cardsList.get(i));
            }
        rv.setLayoutManager(new CGLM(context, 3, GridLayoutManager.HORIZONTAL, false));
        CardsListAdapter tempcla = new CardsListAdapter(temp, context);
        rv.setAdapter(tempcla);
    }

}
