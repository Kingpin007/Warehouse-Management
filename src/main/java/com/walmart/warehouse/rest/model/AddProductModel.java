package com.walmart.warehouse.rest.model;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddProductModel {

	Set<Product> products = new HashSet<Product>();
}
