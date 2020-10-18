package com.immfly.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.immfly.model.Flight;
import com.immfly.model.Location;
import com.immfly.repositories.FlightRepository;
import com.immfly.services.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

	FlightService flightService;
	
	@Mock
	FlightRepository flightRepository;
	
	List<Flight> flights;
	
	@BeforeEach
	void setUp() {
		flights = new ArrayList<>();
		flights.add(flightInfo());
		flightService = new FlightServiceImpl(flightRepository);
	}
	
	@Test
	void getFlightDescription() {
		//given
		String tailNumber = "EC-MYT";
		String flightNumber = "653";
		given(flightRepository.findByTailNumber("EC-MYT")).willReturn(Optional.of(flights));
		//when
		Optional<Flight> flight = flightService.getFlightDescription(tailNumber, flightNumber);
		//then
		assertTrue(flight.isPresent());
	}
	
	@Test
	void getResourseNotFoundWhenTialNumberDoesNotExist() throws Exception {
		// given
		String nonExistentTailNumber = "XX-XXX";
		String flightNumber = "653";
		given(flightRepository.findByTailNumber("XX-XXX")).willReturn(Optional.empty());
		//when
		Optional<Flight> flight = flightService.getFlightDescription(nonExistentTailNumber, flightNumber);
		//then
		assertFalse(flight.isPresent());
	}
	
	@Test
	void getResourseNotFoundWhenFlightNumberDoesNotExist() throws Exception {
		// given
		String tailNumber = "EC-MYT";
		String nonExistentFlightNumber = "000";
		given(flightRepository.findByTailNumber("EC-MYT")).willReturn(Optional.of(flights));
		//when
		Optional<Flight> flight = flightService.getFlightDescription(tailNumber, nonExistentFlightNumber);
		//then
		assertFalse(flight.isPresent());
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
