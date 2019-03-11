package com.walmart.warehouse.mapstruct;

import org.mapstruct.Mapper;

import com.walmart.warehouse.domain.ProductDO;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.rest.model.CreateProductModel;
import com.walmart.warehouse.rest.model.CreateWarehouseModel;
@Mapper
public interface Mapperutility {

	public WarehouseDO getWarehouseDOFromCreateWarehouseModel(CreateWarehouseModel warehouseModel);
	
	public ProductDO getProductDOFromCreateProductModel(CreateProductModel createProductModel);
}
