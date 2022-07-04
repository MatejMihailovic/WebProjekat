package main.java.com.projekat.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Porudzbina implements Serializable{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable( name = "porudzbina_artikli",
                joinColumns = { @JoinColumn(name = "porudzbina_id") },
                inverseJoinColumns = { @JoinColumn(name = "artikal_id") }
    )
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    @Column
    public Date datumIVreme;
    @Column
    private double cena;
    @Column
    private Status status;
    @Column
    private String korisnickoIme;

    public Porudzbina(Restoran restoran, double cena, Status status, String korisnickoIme) {
        this.restoran = restoran;
        datumIVreme = new Date();
        this.cena = cena;
        this.status = status;
        this.korisnickoIme = korisnickoIme;
    }

    public Porudzbina() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatum() {
        return datumIVreme;
    }

    public void setDatum(Date datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addArtikal(Artikal artikal){
        boolean added = poruceniArtikli.add(artikal);
        if(added){
            artikal.getPorudzbine().add(this);
        }
    }
    public void removeArtikal(Artikal artikal){
        boolean remove = poruceniArtikli.remove(artikal);
        if(remove){
            artikal.getPorudzbine().remove(this);
        }
    }
    @Override
    public String toString() {
        return "Porudzbina{" +
                "id=" + id +
                ", restoran=" + restoran +
                ", datum=" + datumIVreme +
                ", cena=" + cena +
                ", status=" + status +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                '}';
    }
}
