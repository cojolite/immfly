package com.immfly.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("Flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String ident;
	public String faFlightID;
	public String airline;
	@JsonProperty("airline_iata") 
	public String airlineIata;
	@JsonProperty("flightnumber")
	public String flightNumber;
	@JsonProperty("tailnumber")
	public String tailNumber;
	public String type;
	@JsonProperty("codeshares")
	public String codeShares;
	public Boolean blocked;
	public Boolean diverted;
	public Boolean cancelled;
	public Location origin;
	public Location destination;
	
}
