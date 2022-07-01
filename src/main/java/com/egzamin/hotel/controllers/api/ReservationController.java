package com.egzamin.hotel.controllers.api;

import com.egzamin.hotel.domain.Guest;
import com.egzamin.hotel.domain.Reservation;
import com.egzamin.hotel.domain.Room;
import com.egzamin.hotel.repository.GuestRepository;
import com.egzamin.hotel.repository.ReservationRepository;
import com.egzamin.hotel.repository.RoomRepository;
import com.egzamin.hotel.service.FindAvailableRoomService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class ReservationController {
    private final ReservationRepository repository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public ReservationController(ReservationRepository repository, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    @GetMapping("/reservations")
    public ResponseEntity<Object> index(){
        List<Reservation> reservations = repository.findAll();
        return new ResponseEntity<Object>(reservations, HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Object> reserve(@RequestBody JSONObject requestBody){
        Room room = roomRepository.findById((Integer) requestBody.get("roomId")).orElseThrow();
        Guest guest = guestRepository.findById(Long.valueOf((Integer) requestBody.get("guestId"))).orElseThrow();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date startDate = formatter.parse((String) requestBody.get("startDate"));
            Date endDate = formatter.parse((String) requestBody.get("endDate"));
            Reservation reservation = new Reservation(guest, room, startDate , endDate);
            if(FindAvailableRoomService.availableInThisTime(room, startDate, endDate)){
                repository.save(reservation);
                return new ResponseEntity<Object>(reservation, HttpStatus.CREATED);
            }
            return new ResponseEntity<Object>(reservation, HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
