package com.reservation.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne()
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;

    @Column
    private Timestamp begin_date;

    @Column
    private Timestamp end_date;

    @Column
    private Integer num_of_people;

    @Column
    private Integer price;

    public Reservation() {
    }

    public Reservation(Timestamp begin_date, Timestamp end_date, Apartment _apartment) {
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.apartment = _apartment;
        num_of_people = _apartment.getNum_of_people();
        price = _apartment.getPrice();
    }

    public Reservation(Reservation reservation){
        this.begin_date = reservation.getBegin_date();
        this.end_date = reservation.getEnd_date();
        this.apartment = reservation.getApartment();
        num_of_people = reservation.getNum_of_people();
        price = reservation.getPrice();
        this.user = reservation.user;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getBegin_date() {
        return begin_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public Integer getNum_of_people() {
        return num_of_people;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBegin_date(Timestamp begin_date) {
        this.begin_date = begin_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public void setNum_of_people(Integer num_of_people) {
        this.num_of_people = num_of_people;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    //Debug-olás céljából
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", apartment=" + apartment +
                ", begin_date=" + begin_date +
                ", end_date=" + end_date +
                ", num_of_people=" + num_of_people +
                ", price=" + price +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        this.num_of_people = apartment.getNum_of_people();
        this.price = apartment.getPrice();
    }
}
