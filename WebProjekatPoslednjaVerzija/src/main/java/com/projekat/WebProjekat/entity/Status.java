package com.projekat.WebProjekat.entity;

public enum Status {
    obrada("Obrada"),
    priprema("Priprema"),
    ceka("Ceka"),
    transport("Transport"),
    dostavljeno("Dostavljeno"),
    otkazano("Otkazano");

    private final String status;

    private Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
