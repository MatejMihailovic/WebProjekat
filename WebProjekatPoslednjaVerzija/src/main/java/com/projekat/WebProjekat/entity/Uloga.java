package com.projekat.WebProjekat.entity;

public enum Uloga {
    Admin("Admin"),
    Menadzer("Menadzer"),
    Dostavljac("Dostavljac"),
    Kupac("Kupac");

    private final String uloga;

    private Uloga(String uloga){
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }
}
