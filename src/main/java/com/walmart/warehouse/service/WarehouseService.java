package com.walmart.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class WarehouseService {

	@Autowired
	WarehouseRepository warehouseRepository;

	public String getProduct() {
		// TODO Auto-generated method stub
		return "HELLO WORLD";
	}
}
