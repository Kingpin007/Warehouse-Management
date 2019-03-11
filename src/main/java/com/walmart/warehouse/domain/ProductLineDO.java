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
	private String productLineKey = UUID.randomUUID().toString();

	@Column(name = "QUANTITY")
	private Double quantity;
	
	@Column(name = "LENGTH")
	private Double length;
	
	@Column(name = "WIDTH")
	private Double width;
	
	@Column(name = "HEIGHT")
	private Double height;
	
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
