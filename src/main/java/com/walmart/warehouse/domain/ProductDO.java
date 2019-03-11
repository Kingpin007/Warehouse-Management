package com.walmart.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
	private ShelfGroupDO shelfGroup;
	
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
	}
}
