package com.ciclocare.repository;

import com.ciclocare.entity.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmeRepository extends JpaRepository<Alarme, Long> {
}
