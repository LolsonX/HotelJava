package com.egzamin.hotel.repository;

import com.egzamin.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
