package com.walmart.warehouse.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateProductModel {

	public Set<ProductLine> productLines = new HashSet<ProductLine>();
	public Double totalQuantity;
	public String unit;
	public Double totalQuantityEmpty;

}
