package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;;

    public Restoran save(Restoran restoran) { return restoranRepository.save(restoran); }

    public Restoran findOne(Long id){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findById(id);
        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }

    public List<Restoran> findAll() { return restoranRepository.findAll(); }

    public List<Restoran> findAll(Specification<Restoran> spec) { return restoranRepository.findAll(spec); }

    public Restoran findOneByLokacija(Lokacija lokacija){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByLokacija(lokacija);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }
    public List<RestoranDto> findByTipRestorana(String tipRestorana){
        List<Restoran> restorani = this.findAll();

        List<RestoranDto> restoranidto = new ArrayList<>();
        for(Restoran r : restorani){
            if(r.getTipRestorana().equals(tipRestorana)){
                restoranidto.add(new RestoranDto(r));
            }
        }

        return restoranidto;
    }
    public List<RestoranDto> getRestorani(RestoranDto dto){
        List<RestoranDto> restorani = new ArrayList<>();
        if(!dto.getNaziv().isEmpty()) {
            restorani.add(new RestoranDto(this.findOneByNaziv(dto.getNaziv())));
        }

        if(dto.getLokacija() != null) {
            restorani.add(new RestoranDto(this.findOneByLokacija(dto.getLokacija())));
        }

        if(!dto.getTipRestorana().isEmpty()) {
            for (RestoranDto rdto : (this.findByTipRestorana(dto.getTipRestorana()))) {
                restorani.add(rdto);
            }
        }
        //Uklanjamo duplikate
        for(int i = 0; i < restorani.size(); i++){
            for(int j = i + 1; j < restorani.size(); j++){
                if(restorani.get(i).getNaziv().equals(restorani.get(j).getNaziv()) || restorani.get(i).getLokacija() == restorani.get(j).getLokacija()){
                    restorani.remove(j);
                    j--;
                }
            }
        }
        return restorani;
    }
    public Restoran findOneByNaziv(String naziv){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByNaziv(naziv);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }

    public void deleteRestoran(Long id){
        Restoran restoran = this.findOne(id);

        restoranRepository.delete(restoran);

    }

}
