package com.walmart.warehouse.mapstruct;

import org.mapstruct.Mapper;

import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.model.CreateWarehouseModel;
@Mapper
public interface Mapperutility {

	public WarehouseDO getWarehouseDOFromCreateWarehouseModel(CreateWarehouseModel warehouseModel);
}
