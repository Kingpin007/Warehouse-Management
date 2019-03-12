package com.walmart.warehouse.domain;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "BOX")
public class BoxDO extends BaseDO{
	
	@Column(name = "BOX_KEY")
	private String boxKey = UUID.randomUUID().toString();

	@Column(name = "HEIGHT")
	private Double height;
	
	@Column(name = "LENGTH")
	private Double length;
	
	@Column(name = "WIDTH")
	private Double width;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	Set<ProductDO> products = new HashSet<>();
	
	public Double getVolume() {
		return length*width*height;
	}
	
	public BoxDO clone() {
		BoxDO boxDO = new BoxDO();
		boxDO.setBoxKey(boxKey);
		boxDO.setHeight(height);
		boxDO.setWidth(width);
		boxDO.setLength(length);
		boxDO.setProducts(products);
		return boxDO;
	}
	
	@PrePersist
	public void initializeKey() {
		if(boxKey == null) {
			boxKey = UUID.randomUUID().toString();
		}
		for(ProductDO productDO : products) {
			if(productDO.getBox() == null) {
				productDO.setBox(this);
			}
		}
		Double d1=length,d2=width,d3=height;
		length = Math.min(d1, Math.min(d2, d3));
		height = Math.max(d1, Math.max(d2, d3));
		if((d1 > d3 && d1 < d2 )||(d1 < d3 && d1 > d2)) {
			width = d1;
		}
		else if((d2 > d3 && d2 < d1)||(d2 < d3 && d2 > d3)) {
			width = d2;
		}
		else {
			width = d3;
		}
	}
}
