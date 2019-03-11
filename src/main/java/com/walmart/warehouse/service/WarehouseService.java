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
		return "HELLO WORLD";
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
	
//	public List<String> insertProduct(CreateProductModel createProductModel){
//		ProductDO productDO = mapperutility.getProductDOFromCreateProductModel(createProductModel);
//		Set<ProductLineDO> productLines = productDO.getProductLines();
//		List<String> shelfIds = new ArrayList<String>();
//		for(ProductLineDO productLineDO : productLines) {
//			ShelfDO shelfDO = getEmptyShelf(productLineDO.getLength(), productLineDO.getWidth(), productLineDO.getHeight());
//			productLineDO.setShelf(shelfDO);
//			shelfIds.add(shelfDO.getShelfKey());
//		}
//		productDO.setProductLines(productLines);
//		this.productRepository.save(productDO);
//		return shelfIds;
//	}
	
//	public ShelfDO getEmptyShelf(Double length, Double width, Double height){
//		ShelfDO bestShelf = null;
//		List<ShelfDO> allShelfPossible = this.shelfRepository.findAll();
//		Double volumeWasted = -1.0;
//		for(ShelfDO shelfDO : allShelfPossible) {
//			if(shelfDO.getShelfHeightRemaining() > height && shelfDO.getShelfLengthRemaining() > length && shelfDO.getShelfWidthRemaining() > width) {
//				Double newVolumeWasted = (shelfDO.getShelfHeightRemaining()-height)*(shelfDO.getShelfLengthRemaining()-length)*(shelfDO.getShelfWidthRemaining() - width);
//				if(volumeWasted == -1.0 || volumeWasted >= newVolumeWasted) {
//					//If best fitting shelf is found till now
//					volumeWasted = newVolumeWasted;
//					bestShelf = shelfDO;
//				}
//			}
//		}
//		return bestShelf;
//	}

	public Set<ShelfDO> pickupProducts(OrderProductModel orderProductModel) {
		// TODO Auto-generated method stub
		Set<Product> products = orderProductModel.getProducts();
		Set<ShelfDO> finalShelves = new HashSet<ShelfDO>();
		for(Product product : products) {
			Integer productKey = product.getProductName().hashCode();
			ProductDO productDO = this.productRepository.findProductDOByProductKey(productKey);
			if(productDO.getTotalQuantity() < product.getTotalQuantity()) {
				//Cannot fullfill order have too few products in stock
				return null;
			}
			Set<ShelfDO> shelves = productDO.getShelfGroup().getShelves();
			Double qtyRemaining = product.getTotalQuantity();
			while(qtyRemaining > 0) {
				for(ShelfDO shelfDO : shelves) {
					if(shelfDO.getProductQuantity() > 0) {
						if(qtyRemaining - shelfDO.getProductQuantity() < 0) {
							qtyRemaining = 0.0;
							shelfDO.setProductQuantity(shelfDO.getProductQuantity()-qtyRemaining);
						}
						else {
							qtyRemaining -= shelfDO.getProductQuantity();
							shelfDO.setProductQuantity(0.0);
						}
						finalShelves.add(shelfDO);
					}
				}
			}
		}
		return finalShelves;
	}

//	private Set<ShelfDO> findPossibleShelfs(Set<ProductLineDO> productLines) {
//		// TODO Auto-generated method stub
//		Set<ShelfDO> shelves = new HashSet<ShelfDO>();
//		for(ProductLineDO productLineDO : productLines) {
//			shelves.add(productLineDO.getShelf());
//		}
//		return shelves;
//	}
}
