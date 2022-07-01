package com.egzamin.hotel.controllers.api;

import com.egzamin.hotel.domain.Guest;
import com.egzamin.hotel.dto.GuestDto;
import com.egzamin.hotel.repository.GuestRepository;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<GuestDto> guests = repository.findAll().stream().map(GuestDto::fromEntity).toList();
        return new ResponseEntity<Object>(guests, HttpStatus.OK);
    }

    @PostMapping("/guests")
    public ResponseEntity<Object> add(@RequestBody JSONObject guest){
        Guest saved = new Guest(guest.getAsString("firstName"), guest.getAsString("lastName"), guest.getAsString("email"), guest.getAsString("phone"));
        return new ResponseEntity<Object>(repository.save(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/guests")
    public ResponseEntity<Object> delete(@RequestParam long id){
        repository.deleteById(id);
        repository.flush();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
