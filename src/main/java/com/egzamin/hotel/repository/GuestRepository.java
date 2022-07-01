package com.egzamin.hotel.repository;

import com.egzamin.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
