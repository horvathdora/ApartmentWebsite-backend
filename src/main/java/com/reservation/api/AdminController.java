package com.reservation.api;

import com.reservation.model.User;
import com.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/users")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.selectAllUsers();
        for (User a: list) {
            System.out.println(a);
        }
        System.out.println();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        return  ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
         User result = userService.getUserById(id).orElse(null);
         return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Long id){
        User result = userService.updateUser(user, id);
        return ResponseEntity.ok().body(result);
    }
}
