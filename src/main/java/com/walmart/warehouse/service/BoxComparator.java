package com.walmart.warehouse.service;

import java.util.Comparator;

import com.walmart.warehouse.domain.BoxDO;

public class BoxComparator implements Comparator<BoxDO> {

	@Override
	public int compare(BoxDO o1, BoxDO o2) {
		// TODO Auto-generated method stub
		if(o1.getVolume() < o2.getVolume()) {
			return -1;
		}
		else if(o1.getVolume() > o2.getVolume()){
			return 1;
		}
		return 0;
	}

	
}
