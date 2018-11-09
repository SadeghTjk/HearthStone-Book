package com.example.rufflez.myapplication;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.TextView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class readJson {
    Context c;
    public static List<Cards> cardsList;
    public static List<Cards> priestCards;

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

                        String cardid = jo.getString("id");
                        String cardName = jo.getString("name");
                        String cardClass = jo.getString("cardClass");
                        if(jo.has("flavor"))
                            cardFlavor = jo.getString("flavor");
                        else cardFlavor = null;
                        if(jo.has("race"))
                            cardRace = jo.getString("race");
                        else cardRace = null;
                        if(jo.has("rarity"))
                            cardRarity = jo.getString("rarity");
                        else cardRarity = null;
                        if(jo.has("set"))
                            cardSet = jo.getString("set");
                        else cardSet = null;
                        if(jo.has("type"))
                            cardType = jo.getString("type");
                        else cardType = null;
                        if(jo.has("text"))
                            cardText = jo.getString("text");
                        else cardText = null;
                        if(jo.has("cost"))
                            cardCost = jo.getInt("cost");
                        else cardCost = -1;
                        if(jo.has("attack"))
                            cardAttack = jo.getInt("attack");
                        else cardAttack = -1;
                        if(jo.has("health"))
                            cardHealth = jo.getInt("health");
                        else cardHealth = -1;

                        cards.setRace(cardRace);
                        cards.setText(cardText);
                        cards.setName(cardName);
                        cards.setCardClass(cardClass);
                        cards.setFlavor(cardFlavor);
                        cards.setRarity(cardRarity);
                        cards.setSet(cardSet);
                        cards.setType(cardType);
                        cards.setCost(cardCost);
                        cards.setAttack(cardAttack);
                        cards.setHealth(cardHealth);
                        cards.setId(cardid);
                        cards.setImage("https://art.hearthstonejson.com/v1/render/latest/enUS/256x/"+cards.getId()+".png");

                        cardsList.add(cards);
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

    //get Sorted Cards List
    public List<Cards> getcardsList(){
        //sort method
        Collections.sort(cardsList, new Comparator<Cards>(){
            public int compare(Cards obj1, Cards obj2) {
                // ## Ascending order
                //return obj1.getCost().compareToIgnoreCase(obj2.getCompanyName); // To compare string values
                 return Integer.compare(obj1.getCost(),obj2.getCost()); // To compare integer values

                // ## Descending order
                // return obj2.getCompanyName().compareToIgnoreCase(obj1.getCompanyName()); // To compare string values
                // return Integer.valueOf(obj2.getId()).compareTo(obj1.getId()); // To compare integer values
            }
        });
        return cardsList;
    }

    //get Priest Cards
//    public List<Cards> getPriestCards(){
//        //final List<Cards> tempcards = cardsList;
//        priestCards = new ArrayList<>();
//        Log.e("first size", "first size"+priestCards.size() );
//        for(int i=0;i<cardsList.size();i++)
//            if(cardsList.get(i).getCardClass().equals("PRIEST")){
//                Log.e("a card", ": "+priestCards.size() );
//                priestCards.add(cardsList.get(i));
//            }
//        //Log.e("priest", "getPriestCards: "+priestCards.size()+priestCards.get(0).getName() );
//        return priestCards;
//    }
}


