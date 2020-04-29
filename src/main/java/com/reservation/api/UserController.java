package com.reservation.api;

import com.reservation.model.Apartment;
import com.reservation.model.Reservation;
import com.reservation.model.User;
import com.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<User> getAllUsers(){
        return userService.selectAllUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping()
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @PostMapping("/{id}/reservation/")
    public User addReservation(@PathVariable("id") Long user_id, @RequestBody Reservation reservation, @RequestBody Long apartment_id){
        return userService.addReservationByUserId(user_id,reservation, apartment_id);
    }

    @GetMapping("/{id}/reservations")
    public List<Reservation> getReservations(@PathVariable("id") Long user_id){
        return userService.getReservationsByUserId(user_id);
    }

    @DeleteMapping("/{id}/reservations/{reservation-id}")
    public List<Reservation> deleteReservation(@PathVariable("id") Long user_id, @PathVariable("reservation-id") Long reservation_id){
        return userService.deleteReservationById(user_id, reservation_id);
    }

    @GetMapping("/reservation/apartments")
    public List<Apartment> getApartmentByDate(@RequestBody Date begin_date, @RequestBody Date end_date){
        return userService.getApartmentsByDate(begin_date, end_date);
    }

}
