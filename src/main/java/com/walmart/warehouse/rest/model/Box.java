package com.walmart.warehouse.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.walmart.warehouse.domain.ProductDO;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Box {

	private Set<ProductDO> products = new HashSet<>();
	private Double length;
	private Double height;
	private Double width;
}
