package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.controller.MenadzerRestController;
import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.repository.MenadzerRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<PorudzbinaDto> returnPorudzbine(Menadzer menadzer){
        Restoran restoran = menadzer.getRestoran();
        List<Porudzbina> porudzbine = this.porudzbinaRepository.findAll();
        List<PorudzbinaDto> dtos = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getRestoran().getId().equals(restoran.getId())){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
