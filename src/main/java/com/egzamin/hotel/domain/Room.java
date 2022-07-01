package com.egzamin.hotel.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    // id => room number, that's why no auto generation
    private @Id int id;

    private boolean airConditioning;
    private boolean seaView;
    private boolean fridge;
    private float price;
    private short beds;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations;

    protected Room(){

    }

    public Room(boolean airConditioning, boolean seaView, boolean fridge, float price, short beds) {
        this.airConditioning = airConditioning;
        this.seaView = seaView;
        this.fridge = fridge;
        this.price = price;
        this.beds = beds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isSeaView() {
        return seaView;
    }

    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public short getBeds() {
        return beds;
    }

    public void setBeds(short beds) {
        this.beds = beds;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return String.format("Room[id=%d]", id);
    }
}
