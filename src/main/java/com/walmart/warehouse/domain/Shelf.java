package com.walmart.warehouse.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "SHELF")
public class Shelf extends BaseDO{
	
	@Column(name = "SHELF_KEY")
	private String shelfKey;
	
	@Column(name = "LATITUDE")
	private double latitude;
	
	@Column(name = "LONGITUDE")
	private double longitude;
	
	@Column(name = "ELEVATION")
	private double elevation;
	
	@Column(name = "SHELF_HEIGHT_REMAINING")
	private double shelfHeightRemaining;
	
	@Column(name = "SHELF_WIDTH_REMAINING")
	private double shelfWidthRemaining;
	
	@Column(name = "SHELF_LENGTH_REMAINING")
	private double shelfLengthRemaining;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELF_GROUP_KEY")
	private ShelfGroupDO shelfGroup;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shelf")
	private Set<ProductLine> productLines;
	
	public double getShelfVolumeRemaining() {
		return this.shelfHeightRemaining*this.shelfWidthRemaining*this.shelfLengthRemaining;
	}
}
