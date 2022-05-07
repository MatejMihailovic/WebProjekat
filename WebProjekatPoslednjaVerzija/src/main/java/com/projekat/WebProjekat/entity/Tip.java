package com.projekat.WebProjekat.entity;

public enum Tip {
    jelo("Jelo"),
    pice("Pice");

    private final String tip;

    private Tip(String tip){
        this.tip = tip;
    }
    public String getTip() {
        return tip;
    }
}
