package main.java.com.projekat.WebProjekat.entity;

public enum Status {
    u_korpi("U korpi"),
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
