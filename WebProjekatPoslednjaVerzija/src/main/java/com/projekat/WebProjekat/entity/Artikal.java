package main.java.com.projekat.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String naziv;
    @Column
    private double cena;
    @Column
    private Tip tip;
    @Column
    private double kolicina;
    @Column
    private String opis;

    @ManyToMany(mappedBy = "poruceniArtikli")
    @JsonIgnore
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "restoran_id")
    @JsonIgnore
    private Restoran restoran;

    @Column(nullable = true, length = 64)
    private String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Artikal(String naziv, double cena, Tip tip, double kolicina, String opis, Restoran restoran) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.restoran = restoran;
    }

    public Artikal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void addPorudzbina(Porudzbina porudzbina){
        boolean added = porudzbine.add(porudzbina);
        if(added){
            porudzbina.getPoruceniArtikli().add(this);
        }
    }
    public void removePorudzbina(Porudzbina porudzbina){
        boolean remove = porudzbine.remove(porudzbina);
        if(remove){
            porudzbina.getPoruceniArtikli().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tip=" + tip +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                '}';
    }
}
