package com.walmart.warehouse.utility;

import com.walmart.warehouse.domain.ShelfDO;

public class UltilityClass {
	
	public static Double findDistanceBetweenTwoShelves(ShelfDO shelf1, ShelfDO shelf2) {
		if(shelf1 == null || shelf2 == null)
			return 0.0;
		Double a1 = Math.abs(shelf1.getLatitude() - shelf1.getShelfGroup().getStartLatitude());
		Double a2 = Math.abs(shelf1.getLatitude() - shelf1.getShelfGroup().getEndLatitude());
		Double c1 = Math.abs(shelf2.getLatitude() - shelf2.getShelfGroup().getStartLatitude());
		Double c2 = Math.abs(shelf2.getLatitude() - shelf2.getShelfGroup().getEndLatitude());
		Double b1 = Math.abs(shelf1.getLongitude() - shelf1.getShelfGroup().getStartLongitude());
		Double b2 = Math.abs(shelf1.getLongitude() - shelf1.getShelfGroup().getEndLongitude());
		Double d1 = Math.abs(shelf2.getLongitude() - shelf2.getShelfGroup().getStartLongitude());
		Double d2 = Math.abs(shelf2.getLongitude() - shelf2.getShelfGroup().getEndLongitude());
		Double path1 = a1+b1+c1+d1;
		Double path2 = a2+b2+c2+d2;
		return Math.min(path1, path2);
	}

}
