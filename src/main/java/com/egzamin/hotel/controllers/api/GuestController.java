package com.egzamin.hotel.controllers.api;

import com.egzamin.hotel.domain.Guest;
import com.egzamin.hotel.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {
    private final GuestRepository repository;

    public GuestController(GuestRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/guests")
    public ResponseEntity<Object> index(){
        List<Guest> guests = repository.findAll();
        return new ResponseEntity<Object>(guests, HttpStatus.OK);
    }
}
