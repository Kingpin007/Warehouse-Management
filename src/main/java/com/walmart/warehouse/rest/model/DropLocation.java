package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DropLocation {

	public Double latitude;
	public Double longitude;
}
