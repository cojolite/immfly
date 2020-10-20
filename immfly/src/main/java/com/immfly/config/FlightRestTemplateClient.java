package com.immfly.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.immfly.model.Flight;

@Component
public class FlightRestTemplateClient {

    private RestTemplate restTemplate;

    public FlightRestTemplateClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Cacheable(cacheNames = "flights", key="{ #tailNumber, #flightNumber }")
    public Flight getFlightInformation(String tailNumber, String flightNumber) {
		ResponseEntity<Flight> restExchange;
		try {
	        restExchange = restTemplate.exchange(
	                        "http://external:8085/v1/flight/{tailNumber}/{flightNumber}",
	                        HttpMethod.GET,
	                        null, Flight.class, tailNumber, flightNumber);
	        return restExchange.getBody();
		} catch (HttpClientErrorException exception) {
			return null;
		}
    }
}
