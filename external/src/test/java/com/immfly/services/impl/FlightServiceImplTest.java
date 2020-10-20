package com.immfly.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.immfly.model.Flight;
import com.immfly.services.FlightService;

public class FlightServiceImplTest {

	FlightService flightService = new FlightServiceImpl();
	
	@Test
	void getFlightDescription() {
		//given
		String tailNumber = "EC-MYT";
		String flightNumber = "653";
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
		//when
		Optional<Flight> flight = flightService.getFlightDescription(tailNumber, nonExistentFlightNumber);
		//then
		assertFalse(flight.isPresent());
	}
	
}
