package com.walmart.warehouse.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.mapstruct.Mapperutility;
import com.walmart.warehouse.mapstruct.MapperutilityImpl;
import com.walmart.warehouse.rest.model.CreateWarehouseModel;

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

//	public List<ShelfDO> pickupProducts(OrderProductModel orderProductModel) {
//		// TODO Auto-generated method stub
//		Set<Product> products = orderProductModel.getProducts();
//		Set<ShelfDO> possibleShelfs = new HashSet<>();
//		Set<ProductDO> productDOs = new HashSet<>();
//		for(Product product : products) {
//			ProductDO productDO = this.productRepository.findProductDOByProductKey(product.getProductKey());
//			productDOs.add(productDO);
//			if(productDO.getTotalQuantity() >= product.getQuantity()) {
//				productDO.setTotalQuantity(productDO.getTotalQuantity()-product.getQuantity());
//				productDO.setTotalQuantityEmpty(product.getQuantity()+productDO.getTotalQuantityEmpty());
//				Set<ProductLineDO> productLines = productDO.getProductLines();
//				possibleShelfs.addAll(findPossibleShelfs(productLines));
//				
//			}
//			else {
//				//Cannot fulfill Order
//				return null;
//			}
//		}
//		for(Product product : products) {
//			Double qtyRemainingToBePickedUp = product.getQuantity();
//			while(qtyRemainingToBePickedUp > 0 ) {
//				
//			}
//		}
//		return null;
//	}

//	private Set<ShelfDO> findPossibleShelfs(Set<ProductLineDO> productLines) {
//		// TODO Auto-generated method stub
//		Set<ShelfDO> shelves = new HashSet<ShelfDO>();
//		for(ProductLineDO productLineDO : productLines) {
//			shelves.add(productLineDO.getShelf());
//		}
//		return shelves;
//	}
}
