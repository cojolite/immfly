package com.immfly.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	public String code;
	public String city;
	@JsonProperty("alternate_ident")
	public String alternateIdent;
	@JsonProperty("airport_name")
	public String airportName;
	
}
