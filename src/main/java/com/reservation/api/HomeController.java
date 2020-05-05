package com.reservation.api;

import com.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HomeController  {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /*
    @GetMapping("/")
    public String home(){
        return("<h1>WELCOME<h1>");
    }

    /*
    @GetMapping("/user")
    public String user(){
        return("<h1>WELCOME User<h1>");
    }
    @GetMapping("/admin")
    public String admin(Authentication authentication){
        return("<h1>WELCOME Admin<h1>");
    }

    @PostMapping(path = "/register")
    public void Register(@RequestBody User user){
        this.userService.addUser(user);
    }

    @GetMapping(path = "login", produces = "application/json")
    public Optional<User> Login(@RequestBody User user){

    }

     */

}
