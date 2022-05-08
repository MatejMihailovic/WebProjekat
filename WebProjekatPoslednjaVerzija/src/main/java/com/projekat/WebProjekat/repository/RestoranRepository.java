package com.projekat.WebProjekat.repository;

import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.entity.Lokacija;
import com.projekat.WebProjekat.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {
    Optional<Restoran> findByLokacija(Lokacija lokacija);

    Optional<Restoran> findByTipRestorana(String tipRestorana);

    Optional<Restoran> findByNaziv(String naziv);
}
