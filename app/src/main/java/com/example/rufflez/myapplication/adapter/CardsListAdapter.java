package com.example.rufflez.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rufflez.myapplication.CardActivity;
import com.example.rufflez.myapplication.Cards;
import com.example.rufflez.myapplication.GlideApp;
import com.example.rufflez.myapplication.R;

import java.util.List;

public class CardsListAdapter extends RecyclerView.Adapter<CardsListAdapter.MyHolder> {
    List<Cards> cardsList;
    Context context;
    public Cards card;

    public CardsListAdapter(List<Cards> cardsList, Context context) {
        this.cardsList = cardsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardsListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cards_list, viewGroup, false);
        return new CardsListAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardsListAdapter.MyHolder myHolder, int i) {
        card = cardsList.get(i);
        GlideApp.with(context).load(card.getImage()).into(myHolder.cardimage);
        myHolder.cardname.setText(card.getName());

    }

    @Override
    public int getItemCount() {
        return cardsList.size();
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
                    bundle.putString("type_card",cardsList.get(getAdapterPosition()).getType());
                    bundle.putInt("attack_card",cardsList.get(getAdapterPosition()).getAttack());

                    showCard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showCard.putExtra("card",bundle);
                    context.startActivity(showCard);
                }
            });
        }

    }
}
