package com.egzamin.hotel.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    private @Id long id;

    @ManyToOne @JoinColumn(name="GUEST_ID", nullable = false) private Guest guest;
    @ManyToOne @JoinColumn(name="ROOM_ID", nullable = false) private Room room;

    private Date startDate;
    private Date endDate;

    protected Reservation(){}

    public Reservation(Guest guest, Room room, Date startDate, Date endDate) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoomId() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

}
