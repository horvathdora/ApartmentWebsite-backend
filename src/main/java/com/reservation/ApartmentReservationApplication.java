package com.reservation;

import com.reservation.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ApartmentReservationApplication {

	public static void main(String[] argv) {
		SpringApplication.run(ApartmentReservationApplication.class);
	}

}
