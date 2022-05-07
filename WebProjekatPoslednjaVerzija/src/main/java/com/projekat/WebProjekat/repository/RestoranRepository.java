package com.projekat.WebProjekat.repository;

import com.projekat.WebProjekat.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {
}
