package com.walmart.warehouse.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "SHELF_GROUP")
public class ShelfGroupDO extends BaseDO{

	@Column(name = "SHELF_GROUP_KEY")
	private String shelfGroupKey = UUID.randomUUID().toString();
	
	@Column(name = "SHELF_GROUP_NAME")
	private String shelfGroupName;
	
	@Column(name = "START_LATITUDE")
	private Double startLatitude;
	
	@Column(name = "START_LONGITUDE")
	private Double startLongitude;
	
	@Column(name = "END_LATITUDE")
	private Double endLatitude;
	
	@Column(name = "END_LONGITUDE")
	private Double endLongitude;
	
	@Column(name = "MAX_QUANTITY")
	private Double maxQuantity;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shelfGroup")
	private Set<ShelfDO> shelves = new HashSet<ShelfDO>(); 
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_KEY")
	private ProductDO product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WAREHOUSE_KEY")
	private WarehouseDO warehouse;
	
	@PrePersist
	public void initilizeKey() {
		if(this.shelfGroupKey == null) {
			shelfGroupKey = UUID.randomUUID().toString();
		}
		for(ShelfDO shelfDO : shelves) {
			if(shelfDO.getShelfGroup() == null) {
				shelfDO.setShelfGroup(this);
			}
		}
		if(this.product.getShelfGroup() == null) {
			product.setShelfGroup(this);
		}
	}
}
