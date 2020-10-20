package com.immfly.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.immfly.model.Flight;
import com.immfly.model.Location;

@SpringBootTest
@Testcontainers
public class FlightRestTemplateClientTest {

	private static final String REDIS_CONTAINER = "redis:3.0.6";
	private static final Integer REDIS_PORT = 6379;

	@Container
	public static FixedHostPortGenericContainer<?> redisContainer = new FixedHostPortGenericContainer<>(REDIS_CONTAINER)
			.withFixedExposedPort(REDIS_PORT, REDIS_PORT);

	@MockBean
	RestTemplate restTemplate;

	@Autowired
	FlightRestTemplateClient flightRestClient;

	@Test
	void testCache() {
		// given
		String tailNumber = "EC-MYT";
		String flightNumber = "653";
		given(restTemplate.exchange("http://external:8085/v1/flight/{tailNumber}/{flightNumber}", HttpMethod.GET, null,
				Flight.class, tailNumber, flightNumber)).willReturn(new ResponseEntity<>(flightInfo(), HttpStatus.OK));
		// when
		flightRestClient.getFlightInformation(tailNumber, flightNumber);
		// when
		flightRestClient.getFlightInformation(tailNumber, flightNumber);
		// then
		verify(restTemplate, times(1)).exchange("http://external:8085/v1/flight/{tailNumber}/{flightNumber}",
				HttpMethod.GET, null, Flight.class, tailNumber, flightNumber);
		// and then
		flightRestClient.getFlightInformation(tailNumber, flightNumber);
		verify(restTemplate, times(1)).exchange("http://external:8085/v1/flight/{tailNumber}/{flightNumber}",
				HttpMethod.GET, null, Flight.class, tailNumber, flightNumber);
	}

	@Test
	void testResourseNotFound() {
		// given
		String tailNumber = "EC-XXX";
		String flightNumber = "653";
		given(restTemplate.exchange("http://external:8085/v1/flight/{tailNumber}/{flightNumber}", HttpMethod.GET, null,
				Flight.class, tailNumber, flightNumber)).willThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
		// when
		Flight fligth = flightRestClient.getFlightInformation(tailNumber, flightNumber);
		// then
		assertNull(fligth);
	}

	private Flight flightInfo() {
		return Flight.builder().ident("IBB653").faFlightID("IBB653-1581399936-airline-0136").airline("IBB")
				.airlineIata("NT").flightNumber("653").tailNumber("EC-MYT").type("Form_Airline").codeShares("IBE123")
				.blocked(false).diverted(false).cancelled(false).origin(originInfo()).destination(destinationInfo())
				.build();
	}

	private Location originInfo() {
		return Location.builder().code("GCXO").city("Tenerife").alternateIdent("TFN")
				.airportName("Tenerife North (Los Rodeos)").build();
	}

	private Location destinationInfo() {
		return Location.builder().code("GCGM").city("La Gomera").alternateIdent("GMZ").airportName("La Gomera").build();
	}
}
