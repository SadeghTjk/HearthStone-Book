package com.example.rufflez.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardActivity extends AppCompatActivity {
    List<Cards> cardsList;
    TextView name_card,set_card,type_card,attack_card;
    ImageView image_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        name_card = findViewById(R.id.name_card);
        set_card = findViewById(R.id.set_card);
        type_card = findViewById(R.id.type_card);
        attack_card = findViewById(R.id.attack_card);
        image_card = findViewById(R.id.image_card);

        Bundle bundle = getIntent().getBundleExtra("card");
        if (bundle != null) {
            Log.e("imageurl", "" + bundle.getString("image_card"));

            Glide.with(this).load(bundle.getString("image_card")).into(image_card);
            name_card.setText(bundle.getString("name_card"));
            set_card.setText(bundle.getString("set_card"));
            type_card.setText(bundle.getString("type_card"));

            if (bundle.getInt("attack_card")>-1) {
                attack_card.setText(String.valueOf(bundle.getInt("attack_card")));
            }else {
                attack_card.setVisibility(View.INVISIBLE);
            }
        }
    }
}
