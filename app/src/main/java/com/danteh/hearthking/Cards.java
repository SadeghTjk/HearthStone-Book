package com.danteh.hearthking;

public class Cards {
    String name;
    int attack;
    String cardClass;
    int cost;
    String id;
    String flavor;
    int health;
    String race;
    String rarity;
    String set;
    String type;
    String text;
    String image;

    public Cards(String name, int attack, String cardClass, int cost, String id, String flavor, int health, String race, String rarity, String set, String type, String text,String image) {
        this.name = name;
        this.attack = attack;
        this.cardClass = cardClass;
        this.cost = cost;
        this.id = id;
        this.flavor = flavor;
        this.health = health;
        this.race = race;
        this.rarity = rarity;
        this.set = set;
        this.type = type;
        this.text = text;
        this.image = image;
    }

    public Cards(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getCardClass() {
        return cardClass;
    }

    public void setCardClass(String cardClass) {
        this.cardClass = cardClass;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

