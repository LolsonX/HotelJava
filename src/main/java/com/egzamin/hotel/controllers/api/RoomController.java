package com.egzamin.hotel.controllers.api;

import com.egzamin.hotel.domain.Room;
import com.egzamin.hotel.dto.RoomDto;
import com.egzamin.hotel.repository.RoomRepository;
import com.egzamin.hotel.service.FindAvailableRoomService;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            Room room = FindAvailableRoomService.findRoom(repository.findAll(),
                    beds,
                    fridge, seaView, airConditioning, formatter.parse(startDate), formatter.parse(endDate));
            return new ResponseEntity<Object>(RoomDto.fromEntity(room), HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/rooms")
    public ResponseEntity<Object> add(@RequestBody JSONObject room) {
        Room newRoom = new Room((boolean) room.get("airConditioning"), (boolean) room.get("seaView"), (boolean) room.get("fridge"), Float.parseFloat(room.getAsString("price")), room.getAsNumber("beds").shortValue());

        newRoom.setId((int) room.get("id"));
        Room saved = repository.save(newRoom);
        return new ResponseEntity<Object>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/rooms")
    public ResponseEntity<Object> delete(@RequestParam int id){
        repository.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
