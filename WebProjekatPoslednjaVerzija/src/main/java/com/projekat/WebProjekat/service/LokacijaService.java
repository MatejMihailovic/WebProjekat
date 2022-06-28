package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.entity.Lokacija;
import main.java.com.projekat.WebProjekat.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public Lokacija save(Lokacija lokacija) { return lokacijaRepository.save(lokacija); }
}
