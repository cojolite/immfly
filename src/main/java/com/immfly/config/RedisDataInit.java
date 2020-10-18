package com.immfly.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immfly.model.Flight;
import com.immfly.model.Location;
import com.immfly.repositories.FlightRepository;

@Component
public class RedisDataInit {

	@Autowired
	private FlightRepository flightRepository;
	
	@PostConstruct
	private void populeteRedis() {
		List<Flight> flightsECMYT = new ArrayList<>();
		String tailNumber = "EC-MYT";
		flightsECMYT.add(flightInfo("653", "IBB653", tailNumber));
		flightsECMYT.add(flightInfo("654", "IBB654", tailNumber));
		flightsECMYT.add(flightInfo("655", "IBB655", tailNumber));
		flightRepository.save(flightsECMYT, tailNumber);
		tailNumber = "EC-MYP";
		List<Flight> flightsECMYP = new ArrayList<>();
		flightsECMYP.add(flightInfo("657", "IBB657", tailNumber));
		flightsECMYP.add(flightInfo("658", "IBB658", tailNumber));
		flightsECMYP.add(flightInfo("659", "IBB659", tailNumber));
		flightRepository.save(flightsECMYP, tailNumber);
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
