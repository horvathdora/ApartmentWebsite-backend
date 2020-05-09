package com.reservation.api;

import com.reservation.model.Apartment;
import com.reservation.model.Reservation;
import com.reservation.model.User;
import com.reservation.service.ApartmentService;
import com.reservation.service.ReservationService;
import com.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private final UserService userService;
    private final ReservationService reservationService;
    private  final ApartmentService apartmentService;

    @Autowired
    public UserController(UserService userService, ReservationService reservationService, ApartmentService apartmentService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.apartmentService = apartmentService;
    }
    

    /*@PostMapping("/{id}/{apartmentID}/{begin_date}/{end_date}")
    public ResponseEntity<User> addReservation(@PathVariable("id") Long user_id,@PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date, @PathVariable("apartmentID") Long apartment_id){
        User result = userService.addReservationByUserId(user_id,reservation, apartment_id);
        return ResponseEntity.ok().body(result);
    }*/
/*
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("id") Long user_id){
        List<Reservation> resultList = userService.getReservationsByUserId(user_id);
        return ResponseEntity.ok().body(resultList);
    }
*/
    @DeleteMapping("/reservations/{reservation-id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("reservation-id") Long reservation_id){
        reservationService.deleteReservationById(reservation_id);
        return ResponseEntity.ok().body("Reservation deleted");
    }

    // Get apartments by date
    @GetMapping("/reservation/apartments/{begin_date}/{end_date}")
    public ResponseEntity<List<Apartment>> getApartmentsByDate(@PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        List<Apartment> resultList = userService.getApartmentsByDate(begin_date, end_date);
        return ResponseEntity.ok().body(resultList);
    }

/*
    @PostMapping("/{username}/{apartmentID}/{begin_date}/{end_date}")
    public ResponseEntity<User> addReservation(@PathVariable("username") String username, @PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date, @PathVariable("apartmentID") Long apartment_id) throws ParseException {
        Optional<User> selectedUser = userService.findByUsername(username);
        User result = userService.addReservationByUserId(selectedUser.get().getId(),begin_date, end_date, apartment_id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{username}/{begin_date}/{end_date}")
    public ResponseEntity<?> addReservation(@PathVariable("username") String username, @RequestBody Apartment apartment,  @PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        Optional<User> selectedUser = userService.findByUsername(username);
        System.out.println("idáig eljut - Controller");
        User result = userService.addReservationByUserId(selectedUser.get().getId(),begin_date, end_date, apartment.getId());
        return ResponseEntity.ok().body(result);
    }
*/
    @PostMapping("/{username}/{begin_date}/{end_date}")
    public ResponseEntity<?> addReservation(@PathVariable("username") String username, @RequestBody Apartment apartment,  @PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        Optional<User> selectedUser = userService.findByUsername(username);
        System.out.println("idáig eljut - Controller");
        User result = userService.addReservationByUserId(selectedUser.get().getId(),begin_date, end_date, apartment.getId());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Reservation> test(@PathVariable("username") String username, @RequestBody Reservation reservation){
        System.out.println("ide eljut");
        Optional<User> findUser = userService.findByUsername(username);
        if(findUser.isPresent()){
            reservation.setUser(findUser.get());
        }
        System.out.println(reservation);
        Reservation result = reservationService.addReservation(reservation);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{username}/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("username") String username){
        Optional<User> selectedUser = userService.findByUsername(username);
        List<Reservation> resultList = userService.getReservationsByUserId(selectedUser.get().getId());
        return ResponseEntity.ok().body(resultList);
    }
}
