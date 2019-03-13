package com.walmart.warehouse.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.warehouse.domain.BoxDO;
import com.walmart.warehouse.domain.ProductDO;
import com.walmart.warehouse.domain.ShelfDO;
import com.walmart.warehouse.domain.ShelfGroupDO;
import com.walmart.warehouse.domain.WarehouseDO;
import com.walmart.warehouse.mapstruct.Mapperutility;
import com.walmart.warehouse.mapstruct.MapperutilityImpl;
import com.walmart.warehouse.rest.model.AddProductModel;
import com.walmart.warehouse.rest.model.Box;
import com.walmart.warehouse.rest.model.CreateWarehouseModel;
import com.walmart.warehouse.rest.model.GenerateWarehouseModel;
import com.walmart.warehouse.rest.model.OrderProductModel;
import com.walmart.warehouse.rest.model.Product;
import com.walmart.warehouse.utility.Graph;

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
	
	@Autowired
	BoxRepository boxRepository;
	
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
	

	public List<String> pickupProducts(OrderProductModel orderProductModel) {
		// TODO Auto-generated method stub
		Set<Product> products = orderProductModel.getProducts();
		Set<ShelfDO> finalShelves = new HashSet<>();
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
							shelfDO.setProductQuantity(shelfDO.getProductQuantity()-qtyRemaining);
							productDO.setTotalQuantity(productDO.getTotalQuantity()-qtyRemaining);
							productDO.setTotalQuantityEmpty(productDO.getTotalQuantityEmpty()+qtyRemaining);
							qtyRemaining = 0.0;
							finalShelves.add(shelfDO);
							break;
						}
						else {
							qtyRemaining -= shelfDO.getProductQuantity();
							productDO.setTotalQuantity(productDO.getTotalQuantity() - shelfDO.getProductQuantity());
							productDO.setTotalQuantityEmpty(productDO.getTotalQuantityEmpty() + shelfDO.getProductQuantity());
							shelfDO.setProductQuantity(0.0);
							finalShelves.add(shelfDO);
						}
					}
				}
			}
			productDO.getShelfGroup().setShelves(shelves);
			this.productRepository.save(productDO);
		}
		int vertices = finalShelves.size();
		int edges = (vertices*(vertices-1))/2;
		Graph graph = new Graph(vertices, edges);
		List<String> pathShelves = graph.calcDistance(finalShelves);
		return pathShelves;
	}

	public List<BoxDO> packupProducts(OrderProductModel orderProductModel) {
		// TODO Auto-generated method stub
		List<BoxDO> boxes = this.boxRepository.findAll();
		Queue<BoxDO> boxQueue = new PriorityQueue<BoxDO>(boxes.size(),new BoxComparator());
		boxQueue.addAll(boxes);
		boxes.clear();
		Set<Product> products = orderProductModel.getProducts();
		
		for(Product product : products) {
			Integer productKey = product.getProductName().hashCode();
			Double productQuantity = product.getTotalQuantity();
			ProductDO productDO = this.productRepository.findByProductKey(productKey);
			while(!boxQueue.isEmpty() && productQuantity > 0) {
				//While Queue is not empty
				BoxDO boxDO = boxQueue.poll();
				if(productDO.getHeight() <= boxDO.getHeight() && productDO.getWidth() <= boxDO.getWidth() && productDO.getLength() <= boxDO.getLength()) {
					BoxDO box0 = boxDO.clone();
					BoxDO box1 = boxDO.clone();//4,4,4
					box1.setLength(box1.getLength() - productDO.getLength());//3,4,4
					boxDO.setLength(boxDO.getLength() - box1.getLength());//1,4,4
					BoxDO box2 = boxDO.clone();//1,4,4
					box2.setWidth(box2.getWidth() - productDO.getWidth());//1,2,4
					boxDO.setWidth(boxDO.getWidth() - box2.getWidth());//1,2,4
					BoxDO box3 = boxDO.clone();//1,2,4
					box3.setHeight(box3.getHeight() - productDO.getHeight());//1,2,1
					boxDO.setHeight(box0.getHeight());
					boxDO.setLength(box0.getLength());
					boxDO.setWidth(box0.getWidth());
					if(boxDO.getProducts() == null) {
						Set<ProductDO> p = new HashSet<ProductDO>();
						boxDO.setProducts(p);
					}
					else {
						boxDO.getProducts().add(productDO);
					}
					boxQueue.add(box1);
					boxQueue.add(box2);
					boxQueue.add(box3);
					Boolean boxExists = false;
					for(BoxDO boxDO2 : boxes) {
						if(boxDO2.getBoxKey().equals(boxDO.getBoxKey())) {
							boxExists = true;
							Set<ProductDO> products1 = boxDO2.getProducts();
							for(ProductDO productDO1 : products1) {
								if(productDO1.getProductKey().equals(productDO.getProductKey())) {
									productDO1.setTotalQuantity(productDO1.getTotalQuantity()+1);
									break;
								}
							}
							break;
						}
					}
					if(boxExists == false)
						boxes.add(boxDO);
					productQuantity -= 1;
				}
			}
		}
		try {
			this.boxRepository.deleteAll(boxes);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return boxes;
	}
	
	public List<String> createBoxes(Double length, Double width, Double height, Integer count) {
		List<String> boxes = new ArrayList<String>();
		while(count > 0) {
			BoxDO boxDO = new BoxDO();
			boxDO.setLength(length);
			boxDO.setWidth(width);
			boxDO.setHeight(height);
			this.boxRepository.save(boxDO);
			boxes.add(boxDO.getBoxKey());
			count -= 1;
		}
		return boxes;
	}

	public String generateWarehouse(GenerateWarehouseModel generateWarehouseModel) {
		CreateWarehouseModel createWarehouseModel = new CreateWarehouseModel();
		createWarehouseModel.generateWarehouse(generateWarehouseModel.getProductNames(), generateWarehouseModel.getStartLatitudes(), 
				generateWarehouseModel.getStartLongitudes(), generateWarehouseModel.getEndLatitudes(), generateWarehouseModel.getEndLongitudes());
		return createWarehouse(createWarehouseModel);
	}

}
