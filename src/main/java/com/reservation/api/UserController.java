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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private final UserService userService;
    private final ReservationService reservationService;

    @Autowired
    public UserController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    //Felhasználó által törölt foglalás
    @DeleteMapping("/reservations/{reservation-id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservation-id") Long reservation_id){
        reservationService.deleteReservationById(reservation_id);
        return ResponseEntity.ok().body("Reservation deleted");
    }

    // Elérhető apartmanok lekérése dátum alapján
    @GetMapping("/reservation/apartments/{begin_date}/{end_date}")
    public ResponseEntity<List<Apartment>> getApartmentsByDate(@PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        List<Apartment> resultList = userService.getApartmentsByDate(begin_date, end_date);
        return ResponseEntity.ok().body(resultList);
    }

    // Felhasználó új foglalást ad le
    @PostMapping("/{username}/{begin_date}/{end_date}")
    public ResponseEntity<User> addReservation(@PathVariable("username") String username, @RequestBody Apartment apartment,  @PathVariable("begin_date") String begin_date, @PathVariable("end_date")  String end_date) throws ParseException {
        Optional<User> selectedUser = userService.findByUsername(username);
        User result = new User();
        if(selectedUser.isPresent()) {
             result = userService.addReservationByUserId(selectedUser.get().getId(), begin_date, end_date, apartment.getId());
        }
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

    // Felhasznához tartozó foglalások
    @GetMapping("/{username}/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("username") String username){
        Optional<User> selectedUser = userService.findByUsername(username);
        List<Reservation> resultList = new ArrayList<>();
        if(selectedUser.isPresent()) {
            resultList = userService.getReservationsByUserId(selectedUser.get().getId());
        }
        return ResponseEntity.ok().body(resultList);
    }
}
