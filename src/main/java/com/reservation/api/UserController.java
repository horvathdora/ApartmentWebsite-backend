package com.reservation.api;

import com.reservation.model.Apartment;
import com.reservation.model.Reservation;
import com.reservation.model.User;
import com.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
//@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    @PostMapping("/{id}/{apartmentID}")
    public ResponseEntity<User> addReservation(@PathVariable("id") Long user_id, @RequestBody Reservation reservation, @PathVariable("apartmentID") Long apartment_id){
        User result = userService.addReservationByUserId(user_id,reservation, apartment_id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("id") Long user_id){
        List<Reservation> resultList = userService.getReservationsByUserId(user_id);
        return ResponseEntity.ok().body(resultList);
    }

    @DeleteMapping("/{id}/reservations/{reservation-id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long user_id, @PathVariable("reservation-id") Long reservation_id){
        userService.deleteReservationById(user_id, reservation_id);
        return ResponseEntity.ok().body("Reservation deleted");
    }

    // Get apartments by date
    // TODO !!!
    @GetMapping("/reservation/apartments/{begin_date}/{end_date}")
    public ResponseEntity<List<Apartment>> getApartmentsByDate(@PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        System.out.println("string: " + begin_date);
        System.out.println("string: " + end_date);

        List<Apartment> resultList = userService.getApartmentsByDate(begin_date, end_date);
        return ResponseEntity.ok().body(resultList);
    }

}
