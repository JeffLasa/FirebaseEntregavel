package com.digitalhouse.firebaseentregavel.model;

public class Game {


    private int id;

    //    @SerializedName("name")
    private String nome;

//    @SerializedName("deck")
    private String deck;

//    @SerializedName("ano")
    private String ano;

//    @SerializedName("image")
    private String image;


    public int getId() {  return id; }

    public void setId(int id) {  this.id = id; }

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