package main.java.com.projekat.WebProjekat.repository;

import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    Optional<List<Komentar>> getByRestoranKomentar(Restoran restoran);
}
