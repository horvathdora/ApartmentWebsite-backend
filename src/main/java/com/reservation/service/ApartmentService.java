package com.reservation.service;

import com.reservation.repository.ApartmentRepository;
import com.reservation.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> selectAllApartments(){
        return apartmentRepository.findAll() ;
    }

    public Apartment addApartment(Apartment apartment){
        Apartment savedApartment = apartmentRepository.save(apartment);
        return savedApartment;
    }

    public Apartment getApartmentById(Long id){
        return apartmentRepository.findApartmentById(id);
    }

    public void deleteApartmentById(Long id){ apartmentRepository.deleteById(id);
    }

    public Apartment updateApartmentById(Apartment apartment){
        Apartment updatedApartment = apartmentRepository.save(apartment);
        return updatedApartment;
    }

}
