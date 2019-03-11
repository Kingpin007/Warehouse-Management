package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Shelf {

	private String shelfName;
	private Double latitude;
	private Double longitude;
	private Double elevation;
	private Double productQuantity;
	private Double maxQuantity;

}