package com.reservation;

import com.reservation.model.Apartment;
import com.reservation.repository.ApartmentRepository;
import com.reservation.service.ApartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ApartmentServiceTest {

    //it will inject the mock with @Mock annotation
    @InjectMocks
    private ApartmentService service;

    //create a mock implementation for the repo
    @Mock
    private ApartmentRepository apartmentRepository;


    @Before
    public void setUp() throws Exception {
        apartmentRepository = mock(ApartmentRepository.class);
        service = new ApartmentService(apartmentRepository);
    }

    @Test
    public void testAddApartment(){
        Apartment addApartment = new Apartment();
        addApartment.setRoom_description("ne legyél null");
        addApartment.setPrice(9400);
        addApartment.setNum_of_people(6);
        addApartment.setId((long) 1);

        Apartment apartmentResult ;
        when(apartmentRepository.save(addApartment)).thenReturn(addApartment);

        apartmentResult  = service.addApartment(addApartment);

        org.junit.Assert.assertNotNull(apartmentResult);
        assertEquals(apartmentResult.getNum_of_people(), addApartment.getNum_of_people());

        Mockito.verify(apartmentRepository).save(addApartment);
   }
}
