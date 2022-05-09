package main.java.com.projekat.WebProjekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable{

    @OneToMany
    @JoinColumn(name = "porudzbina_id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        super.setUloga(Uloga.Dostavljac);
    }

    public Dostavljac() {
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    @Override
    public String toString() {
        return "Dostavljac{" +
                ", porudzbine=" + porudzbine +
                "} " + super.toString();
    }
}
