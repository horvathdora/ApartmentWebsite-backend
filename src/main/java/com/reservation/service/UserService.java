package com.reservation.service;

import com.reservation.repository.ApartmentRepository;
import com.reservation.repository.ReservationRepository;
import com.reservation.model.Apartment;
import com.reservation.model.Reservation;
import com.reservation.model.User;
import com.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, ReservationRepository reservationRepository, ApartmentService apartmentService, ApartmentRepository apartmentRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> selectAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Long id){
        User updatedUser = userRepository.findUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUsername(user.getUsername());
        userRepository.save(updatedUser);
        return updatedUser;
    }

    //Foglalás hozzáadása
    public User addReservationByUserId(Long user_id, Reservation reservation, Long apartment_id){
        Optional<User> optionalUser = userRepository.findById(user_id);
        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartment_id);
        optionalApartment.ifPresent(reservation::setApartment);
        if(optionalUser.isPresent()){
            optionalUser.get().addReservation(reservation);
            reservation.setUser(optionalUser.get());
            reservationRepository.save(reservation);
            return userRepository.save(optionalUser.get());
        }
        return null;
    }

    /*public Reservation addApartmentById(Long reservation_id, Long apartment_id){
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservation_id);
        if(optionalReservation.isPresent()){
            Optional<Apartment> optionalApartment = apartmentService.getApartmentById(apartment_id);
            if (optionalApartment.isPresent()){
                optionalReservation.get().addApartment(optionalApartment.get());
                return optionalReservation.get();
            }
        }
        return null;
    }*/

    //felhasználó általi foglalások listázása
    public List<Reservation> getReservationsByUserId(Long user_id){
        Optional<User> optionalUser = userRepository.findById(user_id);
        return optionalUser.map(User::getReservations).orElse(null);
    }

    //felhasználó által foglalás törlése
    public List<Reservation> deleteReservationById(Long user_id, Long reservation_id){
        Optional<User> optionalUser = userRepository.findById(user_id);
        if(optionalUser.isPresent()){
            Optional<Reservation> optionalReservation = reservationRepository.findById(reservation_id);
            if(optionalReservation.isPresent()){
                optionalUser.get().removeReservation(optionalReservation.get());
                reservationRepository.deleteById(reservation_id);
                userRepository.save(optionalUser.get());
                return optionalUser.map(User::getReservations).orElse(null);
            }
        }
        return null;
    }


    // szerintem azokat listázza amik pont beleesnek ebbe az intervallumba :(
    //nincs transactional, read only fg
    //elérhető apartmanok listázása dátum alapján
    @Transactional(readOnly = true)
    public List<Apartment> getApartmentsByDate(Date begin_date, Date end_date){
       return reservationRepository.findAll()
               .stream()
               .filter(r ->
                       (r.getBegin_date().getTime() <= begin_date.getTime()) && (r.getEnd_date().getTime() <= end_date.getTime()) && (begin_date.getTime() < r.getEnd_date().getTime()) ||
                       (r.getBegin_date().getTime() <= begin_date.getTime()) && (r.getEnd_date().getTime() >= end_date.getTime()) ||
                       (r.getBegin_date().getTime() >= begin_date.getTime()) && (r.getBegin_date().getTime() <= end_date.getTime()) && (r.getEnd_date().getTime() >= end_date.getTime()) ||
                       (r.getBegin_date().getTime() >= begin_date.getTime()) && (r.getEnd_date().getTime() <= end_date.getTime())  )
               .map(Reservation::getApartment)
               .distinct()
               .collect(Collectors.toList());

    }

}
