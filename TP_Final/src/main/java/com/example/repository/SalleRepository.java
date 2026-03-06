package com.example.repository;

import com.example.model.Salle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SalleRepository {

    Salle findById(Long id);
    List<Salle> findAll();
    void save(Salle salle);
    void update(Salle salle);
    void delete(Salle salle);

    // filtrage avec critere
    List<Salle> findByCriteria(Map<String, Object> criteria);
    // filtrage avec chambres
    List<Salle> findAvailableRooms(LocalDateTime start, LocalDateTime end);
    // filtrage !!

    List<Salle> findAllPaginated(int page, int size);

    long count();


}



