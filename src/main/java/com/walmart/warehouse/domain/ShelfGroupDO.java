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
@Table(name = "SHELF_GROUP")
public class ShelfGroupDO extends BaseDO{

	@Column(name = "SHELF_GROUP_KEY")
	private String shelfGroupKey;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shelfGroup")
	private Set<Shelf> shelves; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WAREHOUSE_KEY")
	private WarehouseDO warehouse;
}
