package com.egzamin.hotel.dto;

import com.egzamin.hotel.domain.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReservationDto {
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("room")
    RoomDto room;
    @JsonProperty("guest")
    GuestDto guest;
    public ReservationDto() {}
    public static ReservationDto fromEntity(Reservation reservation){
        ReservationDto dto = new ReservationDto();
        dto.setEndDate(reservation.getEndDate());
        dto.setGuest(GuestDto.fromEntity(reservation.getGuest()));
        dto.setStartDate(reservation.getStartDate());
        dto.setRoom(RoomDto.fromEntity(reservation.getRoomId()));
        return dto;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public GuestDto getGuest() {
        return guest;
    }

    public void setGuest(GuestDto guest) {
        this.guest = guest;
    }
}
