package com.walmart.warehouse.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShelfGroup {

	private String shelfGroupName;
	private Double startLatitude;
	private Double startLongitude;
	private Double endLatitude;
	private Double endLongitude;
	private Double maxQuantity;
	private Product product;
	private Set<Shelf> shelves = new HashSet<Shelf>();

}