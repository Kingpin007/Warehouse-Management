package com.walmart.warehouse.rest.model;

import java.util.Set;

import lombok.Data;

@Data
public class ShelfGroup {

	public Double startLatitude;
	public Double startLongitude;
	public Double endLatitude;
	public Double endLongitude;
	public Set<Shelf> shelves = null;

}