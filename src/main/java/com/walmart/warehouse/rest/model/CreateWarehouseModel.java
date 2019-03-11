package com.walmart.warehouse.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateWarehouseModel {

	private String warehouseName;
	private Set<ShelfGroup> shelfGroups = new HashSet<ShelfGroup>();
	private Set<DropLocation> dropLocations = new HashSet<DropLocation>();

}