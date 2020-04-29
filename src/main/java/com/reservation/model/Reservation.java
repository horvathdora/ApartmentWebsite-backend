package com.reservation.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;

    @Column
    private Date begin_date;

    @Column
    private Date end_date;

    @Column
    private Integer num_of_people;

    @Column
    private Integer price;

    public Reservation() {
    }

    public Reservation(Date begin_date, Date end_date, Apartment _apartment) {
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.apartment = _apartment;
        num_of_people = _apartment.getNum_of_people();
        price = _apartment.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public Date getEnd_date() {
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

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setNum_of_people(Integer num_of_people) {
        this.num_of_people = num_of_people;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    /*public void addApartment(Apartment apartment){
        apartments.add(apartment);
    }*/

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
    }
}
