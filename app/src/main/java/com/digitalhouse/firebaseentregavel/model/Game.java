package com.digitalhouse.firebaseentregavel.model;

import com.google.gson.annotations.SerializedName;

public class Game {



    @SerializedName("name")
    private String nome;

    @SerializedName("deck")
    private String deck;

    @SerializedName("ano")
    private String ano;

    @SerializedName("image")
    private String image;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}