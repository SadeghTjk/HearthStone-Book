package com.example.rufflez.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rufflez.myapplication.adapter.CardsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rufflez on 6/21/15.
 */
public class CoordinatorFragment extends Fragment  {
    RecyclerView recyclerView;
    List<Cards> cardsList;
    Context context ;
    CardsListAdapter cla;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        context = getActivity();
        View rootView = inflater.inflate(R.layout.coordinator_layout, container, false);
        readJson readJson = new readJson(context);
        cardsList = readJson.getcardsList();
        searchView = rootView.findViewById(R.id.appbar_search);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new CardsListAdapter(cardsList, context, new CardsListAdapter.CardsAdapterListener() {
            @Override
            public void onCardsSelected(Cards cards) {
            }
        }));

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.appbar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        cla = new CardsListAdapter(cardsList, context, new CardsListAdapter.CardsAdapterListener() {
            @Override
            public void onCardsSelected(Cards cards) {}});

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    cla.getFilter().filter(newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    cla.getFilter().filter(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);


//
//        SearchManager searchManager = (SearchManager) context.getSystemService(context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.appbar_search).getActionView();
//        searchView.setSearchableInfo(searchManager
//                .getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                cla.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                cla.getFilter().filter(query);
//                return false;
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.appbar_search:
                return false;
                }

        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
//    private void setupRecyclerView(RecyclerView recyclerView){
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
//                VersionModel.data));
    }
//
//    public static class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>{
//        private String[] mValues;
//        private Context mContext;
//
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//
//            public final View mView;
//            public final TextView mTextView;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mTextView = (TextView) view.findViewById(android.R.id.text1);
//            }
//
//        }
//
//        public String getValueAt(int position) {
//            return mValues[position];
//        }
//
//        public SimpleStringRecyclerViewAdapter(Context context, String[] items) {
//            mContext = context;
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(android.R.layout.simple_list_item_1, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, final int position) {
//            holder.mTextView.setText(mValues[position]);
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, getValueAt(position), Snackbar.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.length;
//        }
//    }
}
