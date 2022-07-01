package com.egzamin.hotel.dto;

import com.egzamin.hotel.domain.Room;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDto {
    @JsonProperty("roomNumber")
    private short roomNumber;
    @JsonProperty("beds")
    private int beds;
    @JsonProperty("fridge")
    private boolean fridge;
    @JsonProperty("seaView")
    private boolean seaView;
    @JsonProperty("airConditioning")
    private boolean airConditioning;
    @JsonProperty("price")
    private float price;
    public RoomDto(){}

    public static RoomDto fromEntity(Room room){
        RoomDto dto = new RoomDto();
        dto.setRoomNumber((short) room.getId());
        dto.setAirConditioning(room.isAirConditioning());
        dto.setBeds(room.getBeds());
        dto.setFridge(dto.isFridge());
        dto.setSeaView(room.isSeaView());
        dto.setPrice(room.getPrice());
        return dto;
    }

    public short getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(short roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isSeaView() {
        return seaView;
    }

    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
