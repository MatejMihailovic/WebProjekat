package com.projekat.WebProjekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable{

    @OneToMany
    @JoinColumn(name = "porudzbina_id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private int brojSakupljenihBodova;

    @OneToOne
    private TipKupca tipKupca;

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, TipKupca tipKupca, int brojSakupljenihBodova){
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.tipKupca = tipKupca;
        super.setUloga(Uloga.Kupac);
        this.brojSakupljenihBodova = brojSakupljenihBodova;
    }

    public Kupac() {
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public int getBrojSakupljenihBodova() {
        return brojSakupljenihBodova;
    }

    public void setBrojSakupljenihBodova(int brojSakupljenihBodova) {
        this.brojSakupljenihBodova = brojSakupljenihBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "brojSakupljenihBodova=" + brojSakupljenihBodova +
                ", tipKupca=" + tipKupca +
                "} " + super.toString();
    }
}
