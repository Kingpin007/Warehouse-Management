package com.walmart.warehouse.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SHELF")
public class ShelfDO extends BaseDO{
	
	@Column(name = "SHELF_KEY")
	private String shelfKey = UUID.randomUUID().toString();
	
	@Column(name = "SHELF_NAME")
	private String shelfName;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "ELEVATION")
	private Double elevation;
	
	@Column(name = "PRODUCT_QUANTITY")
	private Double productQuantity;
	
	@Column(name = "MAX_QUANTITY")
	private Double maxQuantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELF_GROUP_KEY")
	private ShelfGroupDO shelfGroup;
	
	@PrePersist
	public void initilizeKey() {
		if(this.shelfKey == null) {
			shelfKey = UUID.randomUUID().toString();
		}
		if(this.latitude == null) {
			latitude = 0.0;
		}
		if(this.longitude == null) {
			longitude = 0.0;
		}
		if(this.productQuantity == null) {
			productQuantity = 0.0;
		}
		if(this.maxQuantity == null) {
			maxQuantity = 0.0;
		}
	}
}
