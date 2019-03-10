package com.walmart.warehouse.mapstruct;

import org.mapstruct.Mapper;

import com.walmart.warehouse.domain.ShelfDO;
import com.walmart.warehouse.domain.ShelfGroupDO;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.model.CreateWarehouseModel;
import com.walmart.warehouse.model.Shelf;
import com.walmart.warehouse.model.ShelfGroup;

@Mapper
public interface Mapperutility {

	public ShelfDO getShelfDOFromShelf(Shelf shelf);
	
	public ShelfGroupDO getShelfGroupDOFromShelfGroup(ShelfGroup shelfGroup);
	
	public WarehouseDO getWarehouseDOFromCreateWarehouseModel(CreateWarehouseModel warehouseModel);
}
