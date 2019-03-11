package com.walmart.warehouse.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBoxModel {

	private Double length;
	private Double width;
	private Double height;
	private Integer count;
}
