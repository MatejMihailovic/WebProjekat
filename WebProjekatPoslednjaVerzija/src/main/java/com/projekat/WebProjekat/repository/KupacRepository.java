package com.projekat.WebProjekat.repository;

import com.projekat.WebProjekat.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {
}
