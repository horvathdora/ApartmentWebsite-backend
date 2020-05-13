package com.reservation.api;
import com.reservation.model.Apartment;
import com.reservation.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/apartments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    // Get all apartments
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Apartment>> selectAllApartments(){
        List<Apartment> list = apartmentService.selectAllApartments();
        return ResponseEntity.ok().body(list);
    }

    // Add new apartment
    @PostMapping
    public ResponseEntity<Apartment> addApartment(@RequestBody Apartment apartment){
        Apartment savedApartment = apartmentService.addApartment(apartment);
        return ResponseEntity.ok().body(savedApartment);
    }

    // Get a single apartment
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable("id") Long id){
        Apartment result = apartmentService.getApartmentById(id);
        return ResponseEntity.ok().body(result);
    }

    // Update apartment
    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartmentById(@PathVariable("id") Long id, @RequestBody Apartment apartment){
        Apartment result = apartmentService.updateApartmentById(apartment, id);
        return ResponseEntity.ok().body(result);
    }

    // Delete an apartment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApartmentById(@PathVariable("id") Long id){
        apartmentService.deleteApartmentById(id);
        return ResponseEntity.ok().body("Apartment deleted");
    }

}
