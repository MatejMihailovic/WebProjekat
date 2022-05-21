package main.java.com.projekat.WebProjekat.repository;

import main.java.com.projekat.WebProjekat.entity.Dostavljac;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Long> {

}
