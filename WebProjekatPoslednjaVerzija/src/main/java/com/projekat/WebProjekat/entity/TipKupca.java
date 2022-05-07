package com.projekat.WebProjekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipKupca implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String ime;
    @Column
    private double popust;
    @Column
    private int trazeniBrojBodova;

    public TipKupca(String ime, double popust, int trazeniBrojBodova) {
        this.ime = ime;
        this.popust = popust;
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    public TipKupca() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getTrazeniBrojBodova() {
        return trazeniBrojBodova;
    }

    public void setTrazeniBrojBodova(int trazeniBrojBodova) {
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    @Override
    public String toString() {
        return "TipKupca{" +
                "ime='" + ime + '\'' +
                ", popust=" + popust +
                ", trazeniBrojBodova=" + trazeniBrojBodova +
                '}';
    }
}
