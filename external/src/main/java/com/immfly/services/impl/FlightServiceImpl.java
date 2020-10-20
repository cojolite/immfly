package com.immfly.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.immfly.model.Flight;
import com.immfly.model.Location;
import com.immfly.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private final List<Flight> flights = getDataFlights();
	
	@Override
	public Optional<Flight> getFlightDescription(String tailNumber, String flightNumber) {
		return flights.stream()
				.filter(
						flight -> flight.getTailNumber().equals(tailNumber) &&
						flight.getFlightNumber().equals(flightNumber)
						)
				.findFirst();
	}

	private List<Flight> getDataFlights() {
		List<Flight> flights = new ArrayList<>();
		String tailNumber = "EC-MYT";
		flights.add(flightInfo("653", "IBB653", tailNumber));
		flights.add(flightInfo("654", "IBB654", tailNumber));
		flights.add(flightInfo("655", "IBB655", tailNumber));
		tailNumber = "EC-MYP";
		flights.add(flightInfo("657", "IBB657", tailNumber));
		flights.add(flightInfo("658", "IBB658", tailNumber));
		flights.add(flightInfo("659", "IBB659", tailNumber));
		return flights;
	}
	
	private Flight flightInfo(String flightNumber, String ident, String tailNumber) {
		return Flight.builder()
				.ident(ident)
				.faFlightID(ident + "-1581399936-airline-0136")
				.airline("IBB")
				.airlineIata("NT")
				.flightNumber(flightNumber)
				.tailNumber(tailNumber)
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
