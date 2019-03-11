package com.walmart.warehouse.rest.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateWarehouseModel {

	public Set<ShelfGroup> shelfGroups = null;
	public Set<DropLocation> dropLocations = null;

}