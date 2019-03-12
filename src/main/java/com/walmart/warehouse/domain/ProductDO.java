package com.walmart.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PRODUCT")
public class ProductDO extends BaseDO{
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_KEY")
	private Integer productKey;
	
	@Column(name = "TOTAL_QUANTITY")
	private Double totalQuantity;
	
	@Column(name = "LENGTH")
	private Double length;
	
	@Column(name = "WIDTH")
	private Double width;
	
	@Column(name = "HEIGHT")
	private Double height;
	
	@Column(name = "UNIT")
	private String unit;
	
	@Column(name = "TOTAL_QUANTITY_EMPTY")
	private Double totalQuantityEmpty;
	
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private ShelfGroupDO shelfGroup;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOX_KEY")
	@JsonIgnore
	private BoxDO box;
	
	@PrePersist
	public void initilizeKey() {
		if(this.productKey == null) {
			if(productName != null)
				productKey = productName.hashCode();
			else
				productKey = 0;
		}
		if(this.unit == null) {
			unit = "no";
		}
		if(this.totalQuantity == null) {
			totalQuantity = 1.0;
		}
		if(this.totalQuantityEmpty == null) {
			totalQuantityEmpty = 0.0;
		}
		if(shelfGroup.getProduct() == null) {
			shelfGroup.setProduct(this);
		}
		Double d1=length,d2=width,d3=height;
		length = Math.min(d1, Math.min(d2, d3));
		height = Math.max(d1, Math.max(d2, d3));
		if((d1 > d3 && d1 < d2 )||(d1 < d3 && d1 > d2)) {
			width = d1;
		}
		else if((d2 > d3 && d2 < d1)||(d2 < d3 && d2 > d3)) {
			width = d2;
		}
		else {
			width = d3;
		}
	}
}
