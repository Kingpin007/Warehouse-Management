package com.walmart.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PRODUCT_LINE")
public class ProductLine extends BaseDO{
	
	@Column(name = "PRODUCT_LINE_KEY")
	private String productLineKey;

	@Column(name = "QUANTITY")
	private double quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELF_KEY")
	private Shelf shelf;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_KEY")
	private Product product;
}
