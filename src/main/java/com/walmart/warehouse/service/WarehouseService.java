package com.walmart.warehouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.walmart.warehouse.domain.ProductDO;
import com.walmart.warehouse.domain.ProductLineDO;
import com.walmart.warehouse.domain.ShelfDO;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.mapstruct.Mapperutility;
import com.walmart.warehouse.mapstruct.MapperutilityImpl;
import com.walmart.warehouse.rest.model.CreateProductModel;
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
	
	public List<String> insertProduct(CreateProductModel createProductModel){
		ProductDO productDO = mapperutility.getProductDOFromCreateProductModel(createProductModel);
		Set<ProductLineDO> productLines = productDO.getProductLines();
		List<String> shelfIds = new ArrayList<String>();
		for(ProductLineDO productLineDO : productLines) {
			ShelfDO shelfDO = getEmptyShelf(productLineDO.getLength(), productLineDO.getWidth(), productLineDO.getHeight());
			productLineDO.setShelf(shelfDO);
			shelfIds.add(shelfDO.getShelfKey());
		}
		productDO.setProductLines(productLines);
		this.productRepository.save(productDO);
		return shelfIds;
	}
	
	public ShelfDO getEmptyShelf(Double length, Double width, Double height){
		ShelfDO bestShelf = null;
		List<ShelfDO> allShelfPossible = this.shelfRepository.findAll();
		Double volumeWasted = -1.0;
		for(ShelfDO shelfDO : allShelfPossible) {
			if(shelfDO.getShelfHeightRemaining() > height && shelfDO.getShelfLengthRemaining() > length && shelfDO.getShelfWidthRemaining() > width) {
				Double newVolumeWasted = (shelfDO.getShelfHeightRemaining()-height)*(shelfDO.getShelfLengthRemaining()-length)*(shelfDO.getShelfWidthRemaining() - width);
				if(volumeWasted == -1.0 || volumeWasted >= newVolumeWasted) {
					//If best fitting shelf is found till now
					volumeWasted = newVolumeWasted;
					bestShelf = shelfDO;
				}
			}
		}
		return bestShelf;
	}

	public List<ShelfDO> pickupProducts(OrderProductModel orderProductModel) {
		// TODO Auto-generated method stub
		Set<Product> products = orderProductModel.getProducts();
		for(Product product : products) {
			ProductDO productDO = this.productRepository.findProductDOByProductKey(product.getProductKey());
			if(productDO.getTotalQuantity() >= product.getQuantity()) {
				productDO.setTotalQuantity(productDO.getTotalQuantity()-product.getQuantity());
				productDO.setTotalQuantityEmpty(product.getQuantity()+productDO.getTotalQuantityEmpty());
				Set<ProductLineDO> productLines = productDO.getProductLines();
				Double qtyRemainingToBePickedUp = product.getQuantity();
				List<ShelfDO> closestShelfs = findClosestShelfs(productLines);
				while(qtyRemainingToBePickedUp > 0) {
					//goto shelf
					//pickup product
					//update shelf
					//update qtyRemainingToBePickedUp
				}
			}
		}
		return null;
	}

	private List<ShelfDO> findClosestShelfs(Set<ProductLineDO> productLines) {
		// TODO Auto-generated method stub
		return null;
	}
}
