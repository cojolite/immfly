package com.immfly.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immfly.model.Flight;
import com.immfly.services.FlightService;

@RestController
@RequestMapping("/v1/flight-information")
public class FlightController {

	FlightService flightService;
	
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(path = "/{tail-number}/{flight-number}", produces = "application/json")
	public ResponseEntity<Flight> flightInformation(
			@PathVariable("tail-number") String tailNumber, 
			@PathVariable("flight-number") String flightNumber) {
		Optional<Flight> flight = flightService.getFlightDescription(tailNumber, flightNumber);
		return flight.isPresent() ?
				new ResponseEntity<>(flight.get(), HttpStatus.OK) : 
				new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
