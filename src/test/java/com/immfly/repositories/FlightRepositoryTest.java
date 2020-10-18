package com.immfly.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.immfly.model.Flight;
import com.immfly.model.Location;

@SpringBootTest
@Testcontainers
public class FlightRepositoryTest {

	private static final String REDIS_CONTAINER = "redis:3.0.6";
	private static final Integer REDIS_PORT = 6379;
	
	@Container
	public static FixedHostPortGenericContainer<?> redisContainer = new FixedHostPortGenericContainer<>(REDIS_CONTAINER).withFixedExposedPort(REDIS_PORT, REDIS_PORT);
	
	@Autowired
	private FlightRepository flightRepository;
	
	@BeforeEach
	void setUp() {
		List<Flight> flights = new ArrayList<>();
		flights.add(flightInfo());
		flightRepository.save(flights, "EC-MYT");
	}
	
	@Test
	void getFlightInformationByTailNumber() {
		//given
		String tailNumber = "EC-MYT";
		//when
		List<Flight> retrievedFlights = flightRepository.findByTailNumber(tailNumber).get();
		//then
		assertThat(retrievedFlights).extracting(Flight::getFlightNumber).contains("653");
	}
	
	@Test
	void getNoFlightInformation() {
		//given
		String tailNumber = "EC-XXX";
		//when
		Optional<List<Flight>> retrievedFlights = flightRepository.findByTailNumber(tailNumber);
		//then
		assertFalse(retrievedFlights.isPresent());
	}
	
	private Flight flightInfo() {
		return Flight.builder()
				.ident("IBB653")
				.faFlightID("IBB653-1581399936-airline-0136")
				.airline("IBB")
				.airlineIata("NT")
				.flightNumber("653")
				.tailNumber("EC-MYT")
				.type("Form_Airline")
				.codeShares("IBE123")
				.blocked(false)
				.diverted(false)
				.cancelled(false)
				.origin(originInfo())
				.destination(destinationInfo())
				.build();
	}
	
	private Location originInfo() {
		return Location.builder()
				.code("GCXO")
				.city("Tenerife")
				.alternateIdent("TFN")
				.airportName("Tenerife North (Los Rodeos)")
				.build();
	}
	
	private Location destinationInfo() {
		return Location.builder()
				.code("GCGM")
				.city("La Gomera")
				.alternateIdent("GMZ")
				.airportName("La Gomera")
				.build();
	}
}
