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
@Table(name = "SHELF")
public class ShelfDO extends BaseDO{
	
	@Column(name = "SHELF_KEY")
	private String shelfKey;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "ELEVATION")
	private Double elevation;
	
	@Column(name = "SHELF_HEIGHT_REMAINING")
	private Double shelfHeightRemaining;
	
	@Column(name = "SHELF_WIDTH_REMAINING")
	private Double shelfWidthRemaining;
	
	@Column(name = "SHELF_LENGTH_REMAINING")
	private Double shelfLengthRemaining;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELF_GROUP_KEY")
	private ShelfGroupDO shelfGroup;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shelf")
	private Set<ProductLineDO> productLines = new HashSet<ProductLineDO>();
	
	public Double getShelfVolumeRemaining() {
		return this.shelfHeightRemaining*this.shelfWidthRemaining*this.shelfLengthRemaining;
	}
	
	@PrePersist
	public void initilizeKey() {
		if(this.shelfKey == null) {
			shelfKey = UUID.randomUUID().toString();
		}
	}
}
