package com.egzamin.hotel.controllers.api;

import com.egzamin.hotel.domain.Room;
import com.egzamin.hotel.dto.RoomDto;
import com.egzamin.hotel.repository.RoomRepository;
import com.egzamin.hotel.service.FindAvailableRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class RoomController {
    private final RoomRepository repository;

    public RoomController(RoomRepository repository){
        this.repository = repository;
    }
    @GetMapping("/rooms")
    public ResponseEntity<Object> index(){
        List<RoomDto> guests = repository.findAll().stream().map(RoomDto::fromEntity).toList();
        return new ResponseEntity<Object>(guests,HttpStatus.OK);
    }

    @GetMapping("/findRoom")
    public ResponseEntity<Object> findRoom(@RequestParam(name = "beds") short beds,
                                           @RequestParam(name = "startDate") String startDate,
                                           @RequestParam(name = "endDate") String endDate,
                                           @RequestParam(name = "airConditioning") boolean airConditioning,
                                           @RequestParam(name = "seaView") boolean seaView,
                                           @RequestParam(name = "fridge") boolean fridge){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            return new ResponseEntity<Object>(FindAvailableRoomService.findRoom(repository.findAll(),
                                                                                beds,
                                                                                fridge, seaView, airConditioning, formatter.parse(startDate), formatter.parse(endDate)), HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
