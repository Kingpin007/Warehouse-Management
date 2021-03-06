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
@Table(name = "DROP_LOCATION")
public class DropLocationDO extends BaseDO{
	
	@Column(name = "DROP_LOCATION_KEY")
	private String dropLocationKey = UUID.randomUUID().toString();
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WAREHOUSE_KEY")
	private WarehouseDO warehouse;
	
	@PrePersist
	public void initilizeKey() {
		if(this.dropLocationKey == null) {
			dropLocationKey = UUID.randomUUID().toString();
		}
	}
}
