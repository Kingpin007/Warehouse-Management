package com.walmart.warehouse.web;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.walmart.warehouse.response.model.WarehouseResponseModel;
import com.walmart.warehouse.rest.model.CreateWarehouseModel;
import com.walmart.warehouse.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/warehouse")
@Api(value = "warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;

	@GetMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	@ApiOperation(value = "getProduct")
	public String helloWorld() {
		return this.warehouseService.getProduct();
	}

	@PostMapping("/save")
	@ResponseBody
	@ApiOperation(value = "put test warehouse object")
	public String createWarehouse() {
		this.warehouseService.saveWarehouse();
		return "Save succesful!";
	}
	
	@PostMapping("/create")
	@ResponseBody
	@ApiOperation(value = "Create new warehouse")
	public WarehouseResponseModel createNewWarehouse(@RequestBody CreateWarehouseModel createWarehouseModel) {
		WarehouseResponseModel warehouseResponseModel = new WarehouseResponseModel();
		warehouseResponseModel.setPayload(this.warehouseService.createWarehouse(createWarehouseModel));
		warehouseResponseModel.setStatus(HttpStatus.CREATED);
		List<String> messages = new ArrayList<String>();
		messages.add("API : Created new warehouse");
		warehouseResponseModel.setMessages(messages);
		return warehouseResponseModel;
	}
	
//	@PostMapping("/product/create")
//	@ResponseBody
//	@ApiOperation(value = "Create and put product on shelf")
//	public WarehouseResponseModel createNewProduct(@RequestBody CreateProductModel createProductModel) {
//		WarehouseResponseModel warehouseResponseModel = new WarehouseResponseModel();
//		warehouseResponseModel.setPayload(this.warehouseService.insertProduct(createProductModel));
//		warehouseResponseModel.setStatus(HttpStatus.CREATED);
//		List<String> messages = new ArrayList<String>();
//		messages.add("API : Created new product, placed productLines in the shelves listed in payload");
//		warehouseResponseModel.setMessages(messages);
//		return warehouseResponseModel;
//	}
	
//	@PostMapping("/product/pickup")
//	@ResponseBody
//	@ApiOperation(value = "Generate PickupList for associate (8 products)")
//	public WarehouseResponseModel createPickupList(@RequestBody OrderProductModel orderProductModel) {
//		WarehouseResponseModel warehouseResponseModel = new WarehouseResponseModel();
//		warehouseResponseModel.setPayload(this.warehouseService.pickupProducts(orderProductModel));
//		warehouseResponseModel.setStatus(HttpStatus.CREATED);
//		List<String> messages = new ArrayList<String>();
//		messages.add("API : Created new product, placed productLines in the shelves listed in payload");
//		warehouseResponseModel.setMessages(messages);
//		return warehouseResponseModel;
//	}
}
