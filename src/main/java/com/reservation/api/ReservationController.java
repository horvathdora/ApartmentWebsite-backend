package com.reservation.api;

import com.reservation.model.Reservation;
import com.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //Ez nem kell
    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationService.addReservation(reservation);
        return ResponseEntity.ok().body(savedReservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> selectAllReservations() {
        List<Reservation> resultList = reservationService.selectAllReservations();
        return ResponseEntity.ok().body(resultList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation result = reservationService.getReservationById(id);
        return ResponseEntity.ok().body(result);
    }

    // Ez nem fog kelleni
    @PutMapping()
    public ResponseEntity<?> updateReservationById(@RequestBody Reservation reservation) {
        Reservation result = reservationService.updateReservationById(reservation);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.ok().body("Reservation deleted");
    }

}
