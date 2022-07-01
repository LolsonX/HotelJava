package com.egzamin.hotel.service;

import com.egzamin.hotel.domain.Room;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FindAvailableRoomService {
    public static Room findRoom(List<Room> rooms, int beds, boolean fridge, boolean seaView, boolean airConditioning, Date startDate, Date endDate){
        return rooms.stream()
                    .filter(room -> availableInThisTime(room, startDate, endDate))
                    .filter(room -> room.getBeds() >= beds)
                    .filter(room -> room.isAirConditioning() == airConditioning && room.isFridge() == fridge && room.isSeaView() == seaView)
                    .max(Comparator.comparingDouble(Room::getPrice)).orElseThrow();
    }

    public static boolean availableInThisTime(Room room, Date startDate, Date endDate){
        return room.getReservations().stream().noneMatch(reservation -> startDate.before(reservation.getEndDate()) &&
                                                                        startDate.after(reservation.getStartDate()) ||
                                                                        endDate.before(reservation.getEndDate()) && endDate.after(reservation.getStartDate()));
    }
}
