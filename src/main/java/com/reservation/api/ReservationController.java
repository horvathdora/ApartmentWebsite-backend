package com.reservation.api;

import com.reservation.model.Reservation;
import com.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    @GetMapping
    public List<Reservation> selectAllReservations() {
        return reservationService.selectAllReservations();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable("id") Long id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping()
    public void updateReservationById(@RequestBody Reservation reservation) {
        reservationService.updateReservationById(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
    }

}
