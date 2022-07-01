package main.java.com.projekat.WebProjekat.configuration;

import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Bean
    public boolean instantiate(){

        Korisnik admin = new Korisnik("admin", "admin123", "Mika", "Mikic", Pol.Musko,new Date(101, Calendar.JULY, 4));
        admin.setUloga(Uloga.Admin);
        korisnikRepository.save(admin);

        Dostavljac dostavljac1 = new Dostavljac("mare123","ftn", "Marko", "Markovic", Pol.Musko, new Date(101, Calendar.JULY, 4));
        dostavljacRepository.save(dostavljac1);

        TipKupca tip1 = new TipKupca("zlatni", 20, 5);
        tipKupcaRepository.save(tip1);

        Lokacija lokacija1 = new Lokacija(200, 100, "Laze Teleckog");
        lokacijaRepository.save(lokacija1);

        Lokacija lokacija2 = new Lokacija(100, 150, "Knez Mihajlova");
        lokacijaRepository.save(lokacija2);

        Restoran restoran1 = new Restoran("Hajduk Veljkov Raj", "kafana", lokacija1);
        restoranRepository.save(restoran1);

        Restoran restoran2 = new Restoran("Podrum 202", "kafana", lokacija2);
        restoranRepository.save(restoran2);

        Kupac kupac1 = new Kupac("pera123", "1234", "Pera", "Peric", Pol.Musko, new Date(99, Calendar.JULY, 4), tip1,20);
        kupacRepository.save(kupac1);

        Kupac kupac2 = new Kupac("mika123", "3004", "Mikac", "Mikic", Pol.Musko, new Date(89, Calendar.SEPTEMBER, 27), tip1,20);
        kupacRepository.save(kupac2);

        Artikal artikal1 = new Artikal("Coca-cola", 70, Tip.pice, 20, "gazirani sok", restoran1);
        artikalRepository.save(artikal1);

        Menadzer menadzer1 = new Menadzer("milena123", "1111", "Milena", "Milenovic", Pol.Zenski, new Date(93, Calendar.FEBRUARY, 16), restoran1);
        menadzerRepository.save(menadzer1);


        Komentar komentar1 = new Komentar(restoran1, kupac1, "Odlicna hrana!", 10);
        komentarRepository.save(komentar1);

        Komentar komentar2 = new Komentar(restoran1, kupac2, "Odlicna usluga!", 9);
        komentarRepository.save(komentar2);


        Komentar komentar3 = new Komentar(restoran2, kupac1, "Fuj!", 3);
        komentarRepository.save(komentar3);

        Komentar komentar4 = new Komentar(restoran2, kupac2, "Neadekvatna higijena!", 1);
        komentarRepository.save(komentar4);

        return true;
    }
}
