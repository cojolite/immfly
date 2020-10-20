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
public class Location {

	public String code;
	public String city;
	@JsonProperty("alternate_ident")
	public String alternateIdent;
	@JsonProperty("airport_name")
	public String airportName;
	
}
