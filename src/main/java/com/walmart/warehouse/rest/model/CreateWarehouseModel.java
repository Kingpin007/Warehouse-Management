package com.walmart.warehouse.rest.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateWarehouseModel {

	private String warehouseName;
	private Set<ShelfGroup> shelfGroups = new HashSet<ShelfGroup>();
	private Set<DropLocation> dropLocations = new HashSet<DropLocation>();
	
	public CreateWarehouseModel generateWarehouse(List<String> productNames, List<Double> startLatitudes, List<Double> startLongitudes,
			List<Double> endLatitudes, List<Double> endLongitudes) {
		if(warehouseName == null) {
			warehouseName = "Anirudh's Warehouse";
		}
		if(shelfGroups == null) {
			shelfGroups = new HashSet<ShelfGroup>();
		}
		if(dropLocations == null) {
			dropLocations = new HashSet<DropLocation>();
			DropLocation dropLocation = new DropLocation();
			dropLocation.setLatitude(0.0);
			dropLocation.setLongitude(0.0);
		}
		for(int i = 0; i< productNames.size();i++) {
			ShelfGroup shelfGroup = new ShelfGroup();
			shelfGroup.setShelfGroupName(productNames.get(i));
			shelfGroup.setEndLatitude(endLatitudes.get(i));
			shelfGroup.setEndLongitude(endLongitudes.get(i));
			shelfGroup.setStartLatitude(startLatitudes.get(i));
			shelfGroup.setStartLongitude(startLongitudes.get(i));
			Double maxQuantity = Math.abs(startLatitudes.get(i) - endLatitudes.get(i));
			shelfGroup.setMaxQuantity(maxQuantity);
			Set<Shelf> shelves = new HashSet<Shelf>();
			for(Double j = shelfGroup.getStartLatitude(); j < shelfGroup.getEndLatitude();j += 1) {
				Shelf shelf = new Shelf();
				shelf.setLatitude(j);
				shelf.setLongitude(endLongitudes.get(i));
				shelf.setShelfName(shelfGroup.getShelfGroupName()+" - "+j.toString());
				shelf.setElevation(0.0);
				shelf.setMaxQuantity(1.0);
				shelf.setProductQuantity(1.0);
				shelves.add(shelf);
			}
			Product product = new Product();
			product.setProductName(productNames.get(i));
			product.setHeight(1.0);
			product.setWidth(1.0);
			product.setLength(1.0);
			product.setTotalQuantity(maxQuantity);
			product.setTotalQuantityEmpty(0.0);
			product.setUnit("no");
			shelfGroup.setShelves(shelves);
			shelfGroup.setProduct(product);
			this.shelfGroups.add(shelfGroup);
		}
		return this;
	}

}