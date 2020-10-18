package com.immfly.services;

import java.util.Optional;

import com.immfly.model.Flight;

public interface FlightService {

	Optional<Flight> getFlightDescription(String tailNumber, String flightNumber);

}
