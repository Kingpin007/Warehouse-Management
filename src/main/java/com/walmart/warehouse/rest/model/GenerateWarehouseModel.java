package com.walmart.warehouse.rest.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenerateWarehouseModel {

	private List<String> productNames; 
	private List<Double> startLatitudes;
	private List<Double> startLongitudes;
	private List<Double> endLatitudes;
	private List<Double> endLongitudes;
}