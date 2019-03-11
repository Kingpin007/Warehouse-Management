package com.walmart.warehouse.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.warehouse.domain.ProductDO;
import com.walmart.warehouse.domain.ShelfDO;
import com.walmart.warehouse.domain.ShelfGroupDO;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.mapstruct.Mapperutility;
import com.walmart.warehouse.mapstruct.MapperutilityImpl;
import com.walmart.warehouse.rest.model.AddProductModel;
import com.walmart.warehouse.rest.model.CreateWarehouseModel;
import com.walmart.warehouse.rest.model.OrderProductModel;
import com.walmart.warehouse.rest.model.Product;

import lombok.Data;

@Service
@Data
public class WarehouseService {

	@Autowired
	WarehouseRepository warehouseRepository;
	
	@Autowired
	ShelfRepository shelfRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	Mapperutility mapperutility = new MapperutilityImpl();

	public String getProduct() {
		// TODO Auto-generated method stub
		return "BULLSHIT FUCKER AGAIN";
	}

	public void saveWarehouse() {
		// TODO Auto-generated method stub
		WarehouseDO warehouseDO = new WarehouseDO();
		warehouseDO.setWarehouseKey(UUID.randomUUID().toString());
		this.warehouseRepository.save(warehouseDO);
	}

	public String createWarehouse(CreateWarehouseModel createWarehouseModel) {
		// TODO Auto-generated method stub
		WarehouseDO warehouseDO = mapperutility.getWarehouseDOFromCreateWarehouseModel(createWarehouseModel);
		this.warehouseRepository.save(warehouseDO);
		return "Save Succesful";
	}
	
	public Set<String> insertProduct(AddProductModel addProductModel){
		Set<Product> products = addProductModel.getProducts();
		Set<String> finalShelves = new HashSet<>();
		for(Product product : products) {
			Integer productKey = product.getProductName().hashCode();
			ProductDO productDO = this.productRepository.findByProductKey(productKey);
			if(productDO.getTotalQuantityEmpty() < product.getTotalQuantity()) {
				//TODO : Not enough space to store orders, inbound failed 
				return null;
			}
			Set<ShelfDO> shelves = productDO.getShelfGroup().getShelves();
			Double qtyRemainingToBeAdded = product.getTotalQuantity();
			productDO.setTotalQuantity(productDO.getTotalQuantity() + qtyRemainingToBeAdded);
			productDO.setTotalQuantityEmpty(Math.max(productDO.getTotalQuantityEmpty() - qtyRemainingToBeAdded, 0.0));
			for(ShelfDO shelfDO : shelves) {
				Double emptyQty = shelfDO.getMaxQuantity() - shelfDO.getProductQuantity();
				if(emptyQty > 0) {
					if(qtyRemainingToBeAdded - emptyQty <= 0) {
						shelfDO.setProductQuantity(qtyRemainingToBeAdded - shelfDO.getProductQuantity());
						qtyRemainingToBeAdded = 0.0;
						finalShelves.add(shelfDO.getShelfName());
						break;
					}
					else {
						//Shelf is full
						qtyRemainingToBeAdded -= emptyQty;
						shelfDO.setProductQuantity(shelfDO.getMaxQuantity());
						finalShelves.add(shelfDO.getShelfName());
					}
				}
			}
			productDO.getShelfGroup().setShelves(shelves);
			this.productRepository.save(productDO);
		}
		return finalShelves;
	}
	

	public Set<String> pickupProducts(OrderProductModel orderProductModel) {
		// TODO Auto-generated method stub
		Set<Product> products = orderProductModel.getProducts();
		Set<String> finalShelves = new HashSet<>();
		for(Product product : products) {
			Integer productKey = product.getProductName().hashCode();
			ProductDO productDO = this.productRepository.findByProductKey(productKey);
			if(productDO.getTotalQuantity() < product.getTotalQuantity()) {
				//Cannot fullfill order have too few products in stock
				return null;
			}
			Set<ShelfDO> shelves = productDO.getShelfGroup().getShelves();
			Double qtyRemaining = product.getTotalQuantity();
			while(qtyRemaining > 0) {
				for(ShelfDO shelfDO : shelves) {
					if(shelfDO.getProductQuantity() > 0) {
						if(qtyRemaining - shelfDO.getProductQuantity() <= 0) {
							qtyRemaining = 0.0;
							shelfDO.setProductQuantity(shelfDO.getProductQuantity()-qtyRemaining);
							finalShelves.add(shelfDO.getShelfName());
							break;
						}
						else {
							qtyRemaining -= shelfDO.getProductQuantity();
							shelfDO.setProductQuantity(0.0);
							finalShelves.add(shelfDO.getShelfName());
						}
					}
				}
			}
			productDO.getShelfGroup().setShelves(shelves);
			this.productRepository.save(productDO);
		}
		return finalShelves;
	}

}
