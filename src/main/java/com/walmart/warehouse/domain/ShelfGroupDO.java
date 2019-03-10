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
	private String shelfGroupKey;
	
	@Column(name = "START_LATITUDE")
	private Double startLatitude;
	
	@Column(name = "START_LONGITUDE")
	private Double startLongitude;
	
	@Column(name = "END_LATITUDE")
	private Double endLatitude;
	
	@Column(name = "END_LONGITUDE")
	private Double endLongitude;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shelfGroup")
	private Set<ShelfDO> shelves = new HashSet<ShelfDO>(); 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WAREHOUSE_KEY")
	private WarehouseDO warehouse;
	
	@PrePersist
	public void initilizeKey() {
		if(this.shelfGroupKey == null) {
			shelfGroupKey = UUID.randomUUID().toString();
		}
	}
}
