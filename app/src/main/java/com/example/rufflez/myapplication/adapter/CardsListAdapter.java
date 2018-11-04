package com.example.rufflez.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import com.bumptech.glide.Glide;
import com.example.rufflez.myapplication.CardActivity;
import com.example.rufflez.myapplication.Cards;
import com.example.rufflez.myapplication.myGlideApp;
import com.example.rufflez.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CardsListAdapter extends RecyclerView.Adapter<CardsListAdapter.MyHolder> implements Filterable {
    List<Cards> cardsList,filteredcards;
    Context context;
    CardsAdapterListener listener;
    public Cards card;

    public CardsListAdapter(List<Cards> cardsList, Context context,CardsAdapterListener listener) {
        this.cardsList = cardsList;
        this.filteredcards = cardsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardsListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cards_list, viewGroup, false);
        return new CardsListAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardsListAdapter.MyHolder myHolder, int i) {
        card = filteredcards.get(i);
        Glide.with(context).load(card.getImage()).into(myHolder.cardimage);
        myHolder.cardname.setText(card.getName());

    }

    @Override
    public int getItemCount() {
        return filteredcards.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView cardname;
        ImageView cardimage;
        RelativeLayout cardView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardname = itemView.findViewById(R.id.cardname);
            cardimage = itemView.findViewById(R.id.cardimage);
            cardView = itemView.findViewById(R.id.cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showCard = new Intent(context, CardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("image_card",cardsList.get(getAdapterPosition()).getImage());
                    bundle.putString("name_card",cardsList.get(getAdapterPosition()).getName());
                    bundle.putString("set_card",cardsList.get(getAdapterPosition()).getSet());
                    bundle.putString("class_card",cardsList.get(getAdapterPosition()).getCardClass());
                    bundle.putString("type_card",cardsList.get(getAdapterPosition()).getType());
                    bundle.putString("flavor_card",cardsList.get(getAdapterPosition()).getFlavor());
                    bundle.putString("text_card",cardsList.get(getAdapterPosition()).getText());
                    bundle.putString("rarity_card",cardsList.get(getAdapterPosition()).getRarity());
                    bundle.putString("race_card",cardsList.get(getAdapterPosition()).getRace());
                    bundle.putInt("attack_card",cardsList.get(getAdapterPosition()).getAttack());

                    showCard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showCard.putExtra("card",bundle);
                    context.startActivity(showCard);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onCardsSelected(filteredcards.get(getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredcards = cardsList;
                } else {
                    List<Cards> filteredList = new ArrayList<>();
                    for (Cards row : cardsList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredcards = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredcards;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredcards = (ArrayList<Cards>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CardsAdapterListener {
        void onCardsSelected(Cards cards);
    }
}
