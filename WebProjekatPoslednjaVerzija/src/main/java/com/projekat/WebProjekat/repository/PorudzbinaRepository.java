package main.java.com.projekat.WebProjekat.repository;

import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
    Optional<Porudzbina> getByStatus(Status status);
}
