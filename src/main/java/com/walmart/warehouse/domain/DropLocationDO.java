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
@Table(name = "DROP_LOCATION")
public class DropLocationDO extends BaseDO{
	
	@Column(name = "DROP_LOCATION_KEY")
	private String dropLocationKey;
	
	@Column(name = "LATITUDE")
	private double latitude;
	
	@Column(name = "LOGITUDE")
	private double longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WAREHOUSE_KEY")
	private WarehouseDO warehouse;
}
