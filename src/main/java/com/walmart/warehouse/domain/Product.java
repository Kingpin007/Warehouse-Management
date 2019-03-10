package com.walmart.warehouse.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseDO{
	
	@Column(name = "PRODUCT_KEY")
	private String productKey;

	@Column(name = "TOTAL_QUANTITY")
	private double totalQuantity;
	
	@Column(name = "UNIT")
	private String unit;
	
	@Column(name = "TOTAL_QUANTITY_EMPTY")
	private double totalQuantityEmpty;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
	private Set<ProductLine> productLines;
}
