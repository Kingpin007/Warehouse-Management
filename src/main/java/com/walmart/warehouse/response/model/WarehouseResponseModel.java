package com.walmart.warehouse.response.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class WarehouseResponseModel {

	private HttpStatus status;
	
	private List<String> messages;
	
	private Object payload;
}
