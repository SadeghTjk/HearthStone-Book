package com.example.rufflez.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class readJson {
    Context c;
    public static List<Cards> cardsList;

    String cardRace,cardFlavor,cardText,cardRarity,cardSet,cardType;
    int cardHealth,cardAttack,cardCost;

    readJson(Context c){
        this.c = c;
    }
    //loads item database json
    public boolean getItemsFromJson() {
        cardsList = new ArrayList<>();
        String url = "https://api.hearthstonejson.com/v1/27358/enUS/cards.collectible.json";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("read json cards", "onResponse: cards readed");
                try {
                    //JSONObject jsonObject = new JSONObject(response);

                    //JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        Cards cards = new Cards();

                        String cardName = jo.getString("name");
                        String cardClass = jo.getString("cardClass");
                        if(jo.has("flavor"))
                            cardFlavor = jo.getString("flavor");
                        if(jo.has("race"))
                            cardRace = jo.getString("race");
                        if(jo.has("rarity"))
                            cardRarity = jo.getString("rarity");
                        if(jo.has("set"))
                            cardSet = jo.getString("set");
                        if(jo.has("type"))
                            cardType = jo.getString("type");
                        if(jo.has("text"))
                            cardText = jo.getString("text");
                        if(jo.has("cost"))
                            cardCost = jo.getInt("cost");
                        if(jo.has("attack"))
                            cardAttack = jo.getInt("attack");
                            else cardAttack = -1;
                        if(jo.has("health"))
                            cardHealth = jo.getInt("health");
                        String cardid = jo.getString("id");

                        cards.setName(cardName);
                        cards.setCardClass(cardClass);
                        cards.setFlavor(cardFlavor);
                        cards.setRace(cardRace);
                        cards.setRarity(cardRarity);
                        cards.setSet(cardSet);
                        cards.setType(cardType);
                        cards.setText(cardText);
                        cards.setCost(cardCost);
                        cards.setAttack(cardAttack);
                        cards.setHealth(cardHealth);
                        cards.setId(cardid);
                        cards.setImage("https://art.hearthstonejson.com/v1/render/latest/enUS/256x/"+cards.getId()+".png");

                        cardsList.add(cards);
                        Log.e("cards that added", "Image: "+cards.getImage());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(c, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(stringRequest);

        return true;
    }
    public List<Cards> getcardsList(){
        return cardsList;
    }
}


