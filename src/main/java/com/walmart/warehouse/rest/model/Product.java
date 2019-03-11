package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {

	private String productName;
	private Double totalQuantity;
	private String unit;
	private Double totalQuantityEmpty;
	private Double length;
	private Double width;
	private Double height;
}
