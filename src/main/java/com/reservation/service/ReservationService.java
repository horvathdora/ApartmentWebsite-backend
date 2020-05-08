package com.reservation.service;

import com.reservation.model.Reservation;
import com.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> selectAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.findReservationById(id);
    }

    public Reservation addReservation(Reservation reservation){
         return reservationRepository.save(reservation);
    }

    public void deleteReservationById(Long id){
         reservationRepository.deleteById(id);
    }

    public Reservation updateReservationById(Reservation reservation){
        return reservationRepository.save(reservation);
    }

}
