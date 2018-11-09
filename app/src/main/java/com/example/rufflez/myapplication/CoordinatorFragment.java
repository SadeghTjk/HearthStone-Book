package com.example.rufflez.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.rufflez.myapplication.adapter.CGLM;
import com.example.rufflez.myapplication.adapter.CardsListAdapter;

import java.util.List;

/**
 * Created by rufflez on 6/21/15.
 */
public class CoordinatorFragment extends Fragment {
    RecyclerView recyclerView;
    List<Cards> cardsList;
    Context context;
    CardsListAdapter cla;
    private SearchView searchView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View rootView = inflater.inflate(R.layout.coordinator_layout, container, false);
        readJson readJson = new readJson(context);
        cardsList = readJson.getcardsList();
        searchView = rootView.findViewById(R.id.appbar_search);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        cla = new CardsListAdapter(cardsList, context,recyclerView);
        recyclerView.setLayoutManager(new CGLM(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(cla);

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

}
