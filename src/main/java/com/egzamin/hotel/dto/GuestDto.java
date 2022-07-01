package com.egzamin.hotel.dto;

import com.egzamin.hotel.domain.Guest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestDto {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;

    public GuestDto() {}

    public static GuestDto fromEntity(Guest guest){
        GuestDto dto = new GuestDto();
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        return dto;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String firstName) {
        this.lastName = firstName;
    }

}
