package main.java.com.projekat.WebProjekat.entity;

public enum Pol {
    Musko("Musko"),
    Zenski("Zensko");

    private final String pol;

    private Pol(String pol) {
        this.pol = pol;
    }

    public String getPol() {
        return pol;
    }
}
