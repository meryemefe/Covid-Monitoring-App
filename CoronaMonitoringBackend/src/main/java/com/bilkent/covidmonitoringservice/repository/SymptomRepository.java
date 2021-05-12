package com.bilkent.covidmonitoringservice.repository;

import com.bilkent.covidmonitoringservice.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    Optional<Symptom> findById(Long id);
    Optional<Symptom> findByUserIdAndDate(Long userId, LocalDate date);
    List<Symptom> findAllByUserIdOrderByDateDesc(Long userId);

}
