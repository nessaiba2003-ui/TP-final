package com.example.repository;

import com.example.model.Reservation;
import com.example.model.Salle;
import com.example.model.StatutReservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    Reservation create(Reservation reservation);
    Reservation update(Reservation reservation);

    void delete(Long id);
 // effectuer le filtrage! avec le statut  la date
    Reservation findById(Long id);

    List<Reservation> findByStatut(StatutReservation statut);

    List<Reservation> findBySalleAndDateRange(Salle salle, LocalDateTime debut, LocalDateTime fin);

}
