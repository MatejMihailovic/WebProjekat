package com.projekat.WebProjekat.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kupac_id")
    private Kupac kupacKomentar;

    @ManyToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoranKomentar;

    @Column
    private String tekstKomentara;
    @Column
    private int ocena;

    public Komentar(Restoran restoranKomentar, Kupac kupacKomentar, String tekstKomentara, int ocena) {
        this.restoranKomentar = restoranKomentar;
        this.kupacKomentar = kupacKomentar;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    public Komentar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Kupac getKupacKomentar() {
        return kupacKomentar;
    }

    public void setKupacKomentar(Kupac kupacKomentar) {
        this.kupacKomentar = kupacKomentar;
    }

    public Restoran getRestoranKomentar() {
        return restoranKomentar;
    }

    public void setRestoranKomentar(Restoran restoranKomentar) {
        this.restoranKomentar = restoranKomentar;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
