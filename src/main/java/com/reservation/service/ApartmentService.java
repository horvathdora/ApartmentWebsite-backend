package com.reservation.service;

import com.reservation.repository.ApartmentRepository;
import com.reservation.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return apartmentRepository.save(apartment);
    }

    public Apartment getApartmentById(Long id){
        return apartmentRepository.findApartmentById(id);
    }

    public void deleteApartmentById(Long id){ apartmentRepository.deleteById(id);
    }

    public Apartment updateApartmentById(Apartment apartment, Long id){
        Apartment updatedApartment = apartmentRepository.findApartmentById(id);
        updatedApartment.setNum_of_people(apartment.getNum_of_people());
        updatedApartment.setPrice(apartment.getPrice());
        updatedApartment.setRoom_description(apartment.getRoom_description());
        apartmentRepository.save(updatedApartment);
        return updatedApartment;
    }

}
