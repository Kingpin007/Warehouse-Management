package com.walmart.warehouse.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PRODUCT_LINE")
public class ProductLineDO extends BaseDO{
	
	@Column(name = "PRODUCT_LINE_KEY")
	private String productLineKey;

	@Column(name = "QUANTITY")
	private Double quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELF_KEY")
	private ShelfDO shelf;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_KEY")
	private ProductDO product;
	
	@PrePersist
	public void initilizeKey() {
		if(this.productLineKey == null) {
			productLineKey = UUID.randomUUID().toString();
		}
	}
}
