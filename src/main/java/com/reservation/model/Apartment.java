package com.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "apartment")
public class Apartment implements Comparable<Apartment>{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer price;

    @Column
    private Integer num_of_people;

    @Column
    private String room_description;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment")
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return getId().equals(apartment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Apartment() {
    }

    public Apartment(Integer _price, Integer _num_of_people, String _room_description) {
        this.price = _price;
        this.num_of_people = _num_of_people;
        this.room_description = _room_description;
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNum_of_people() {
        return num_of_people;
    }

    public String getRoom_description() {
        return room_description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setNum_of_people(Integer num_of_people) {
        this.num_of_people = num_of_people;
    }

    public void setRoom_description(String room_description) {
        this.room_description = room_description;
    }

    @Override
    public int compareTo(Apartment o) {
        return this.equals(o) ? 0 : 1;
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
