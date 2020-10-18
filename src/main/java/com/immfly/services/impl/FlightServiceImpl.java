package com.immfly.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.immfly.model.Flight;
import com.immfly.repositories.FlightRepository;
import com.immfly.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;
	
	public FlightServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public Optional<Flight> getFlightDescription(String tailNumber, String flightNumber) {
		Optional<List<Flight>> flights = flightRepository.findByTailNumber(tailNumber);
		return flights.orElse(new ArrayList<>()).stream().filter(flight -> flight.getFlightNumber().equals(flightNumber)).findFirst();
	}

}
