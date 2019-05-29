package com.danteh.hearthking;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CardActivity extends AppCompatActivity {

    TextView name_card,set_card,type_card,attack_card,class_card,flavor_card,text_card,rarity_card,race_card;
    ImageView image_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        name_card = findViewById(R.id.name_card);
        set_card = findViewById(R.id.set_card);
        type_card = findViewById(R.id.type_card);
        attack_card = findViewById(R.id.attack_card);
        class_card = findViewById(R.id.class_card);
        flavor_card = findViewById(R.id.flavor_card);
        race_card = findViewById(R.id.race_card);
        text_card = findViewById(R.id.text_card);
        rarity_card = findViewById(R.id.rarity_card);
        image_card = findViewById(R.id.image_card);

        Bundle bundle = getIntent().getBundleExtra("card");
        if (bundle != null) {
            Log.e("imageurl", "" + bundle.getString("image_card"));

            Glide.with(this).load(bundle.getString("image_card")).into(image_card);
            name_card.setText(bundle.getString("name_card"));
            set_card.setText(bundle.getString("set_card"));
            type_card.setText(bundle.getString("type_card"));
            class_card.setText(bundle.getString("class_card"));
            flavor_card.setText(bundle.getString("flavor_card"));
            if(bundle.getString("text_card") != null)
                text_card.setText(Html.fromHtml(bundle.getString("text_card")));
            rarity_card.setText(bundle.getString("rarity_card"));
            race_card.setText(bundle.getString("race_card"));
            if (bundle.getInt("attack_card")>-1) {
                attack_card.setText(String.valueOf(bundle.getInt("attack_card")));
            }else
                attack_card.setVisibility(View.INVISIBLE);
        }
    }
}
