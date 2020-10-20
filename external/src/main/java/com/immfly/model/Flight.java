package com.immfly.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
	
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
