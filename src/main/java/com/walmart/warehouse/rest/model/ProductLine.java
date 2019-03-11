package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductLine {

	public Double quantity;
	public Double length;
	public Double width;
	public Double height;
}
