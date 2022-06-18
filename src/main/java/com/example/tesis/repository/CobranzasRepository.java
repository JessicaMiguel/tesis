package com.example.tesis.repository;
import com.example.tesis.domain.Cobranza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobranzasRepository extends JpaRepository<Cobranza, Long> {



}