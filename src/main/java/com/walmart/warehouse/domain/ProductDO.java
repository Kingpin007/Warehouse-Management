package com.walmart.warehouse.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PRODUCT")
public class ProductDO extends BaseDO{
	
	@Column(name = "PRODUCT_KEY")
	private String productKey = UUID.randomUUID().toString();

	@Column(name = "TOTAL_QUANTITY")
	private Double totalQuantity;
	
	@Column(name = "UNIT")
	private String unit;
	
	@Column(name = "TOTAL_QUANTITY_EMPTY")
	private Double totalQuantityEmpty;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
	private Set<ProductLineDO> productLines = new HashSet<ProductLineDO>();
	
	@PrePersist
	public void initilizeKey() {
		if(this.productKey == null) {
			productKey = UUID.randomUUID().toString();
		}
		if(this.unit == null) {
			unit = "no";
		}
		if(this.totalQuantity == null) {
			totalQuantity = 0.0;
		}
		if(this.totalQuantityEmpty == null) {
			totalQuantityEmpty = 0.0;
		}
		for(ProductLineDO productLineDO : productLines) {
			if(productLineDO.getProduct() == null) {
				productLineDO.setProduct(this);
			}
		}
	}
}
