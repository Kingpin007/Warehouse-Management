package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {

	private String productKey;
	private Double quantity;
	private Double length;
	private Double width;
	private Double height;
}
