package com.immfly.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immfly.config.FlightRestTemplateClient;
import com.immfly.model.Flight;

@RestController
@RequestMapping("/v1/flight-information")
public class FlightController {

	private FlightRestTemplateClient flightRestClient;
	
	public FlightController(FlightRestTemplateClient FlightRestTemplateClient) {
		this.flightRestClient = FlightRestTemplateClient;
	}

	@GetMapping(path = "/{tailNumber}/{flightNumber}", produces = "application/json")
	public ResponseEntity<Flight> flightInformation(
			@PathVariable String tailNumber, 
			@PathVariable String flightNumber) {
		Flight flight = flightRestClient.getFlightInformation(tailNumber, flightNumber);
		return flight != null ?
				new ResponseEntity<>(flight, HttpStatus.OK) : 
				new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
