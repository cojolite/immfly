package com.immfly.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.immfly.model.Flight;
import com.immfly.model.Location;
import com.immfly.services.FlightService;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

	@MockBean
	FlightService flightService;
 
	@Autowired
    MockMvc mockMvc;
    
	@Test
	void getFlightInformationByTailAndFlightNumber() throws Exception {
		// given
		String tailNumber = "EC-MYT";
		String flightNumber = "653";
		given(flightService.getFlightDescription(tailNumber, flightNumber)).willReturn(Optional.of(flightInfo()));
		// when
		mockMvc.perform(get("/v1/flight/" + tailNumber + "/" + flightNumber))
		// then
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.ident", is("IBB653")))
		.andExpect(jsonPath("$.faFlightID", is("IBB653-1581399936-airline-0136")))
		.andExpect(jsonPath("$.airline", is("IBB")))
		.andExpect(jsonPath("$.airline_iata", is("NT")))
		.andExpect(jsonPath("$.flightnumber", is("653")))
		.andExpect(jsonPath("$.tailnumber", is("EC-MYT")))
		.andExpect(jsonPath("$.type", is("Form_Airline")))
		.andExpect(jsonPath("$.codeshares", is("IBE123")))
		.andExpect(jsonPath("$.blocked", is(false)))
		.andExpect(jsonPath("$.diverted", is(false)))
		.andExpect(jsonPath("$.cancelled", is(false)))
		.andExpect(jsonPath("$.origin.code", is("GCXO")))
		.andExpect(jsonPath("$.origin.city", is("Tenerife")))
		.andExpect(jsonPath("$.origin.alternate_ident", is("TFN")))
		.andExpect(jsonPath("$.origin.airport_name", is("Tenerife North (Los Rodeos)")))
		.andExpect(jsonPath("$.destination.code", is("GCGM")))
		.andExpect(jsonPath("$.destination.city", is("La Gomera")))
		.andExpect(jsonPath("$.destination.alternate_ident", is("GMZ")))
		.andExpect(jsonPath("$.destination.airport_name", is("La Gomera")));
	}
	
	@Test
	void getResourseNotFoundWhenTialNumberDoesNotExist() throws Exception {
		// given
		String nonExistentTailNumber = "XX-XXX";
		String flightNumber = "653";
		given(flightService.getFlightDescription(nonExistentTailNumber, flightNumber)).willReturn(Optional.empty());
		// when
		mockMvc.perform(get("/v1/flight/" + nonExistentTailNumber + "/" + flightNumber))
		// then
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	void getResourseNotFoundWhenFlightNumberDoesNotExist() throws Exception {
		// given
		String tailNumber = "EC-MYT";
		String nonExistentFlightNumber = "000";
		given(flightService.getFlightDescription(tailNumber, nonExistentFlightNumber)).willReturn(Optional.empty());
		// when
		mockMvc.perform(get("/v1/flight/" + tailNumber + "/" + nonExistentFlightNumber))
		// then
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
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
