package com.walmart.warehouse.utility;

import com.walmart.warehouse.domain.ShelfDO;

public class UltilityClass {
	
	public static Double findDistanceBetweenTwoShelves(ShelfDO shelf1, ShelfDO shelf2) {
		if(shelf1 == null || shelf2 == null)
			return 0.0;
		Double P1 = shelf1.getLongitude()-shelf1.getShelfGroup().getStartLongitude();
		Double P2 = shelf1.getShelfGroup().getEndLongitude()- shelf1.getLongitude();

		Double P3 = shelf2.getLongitude()-shelf2.getShelfGroup().getStartLongitude();
		Double P4 = shelf2.getShelfGroup().getEndLongitude()- shelf2.getLongitude();
		Double V1 = P1+P3+getManhattanDistance(shelf1.getShelfGroup().getStartLatitude(),
												shelf2.getLatitude(),
												shelf1.getShelfGroup().getStartLongitude(),
												shelf2.getShelfGroup().getStartLongitude());
		Double V2 = P1+P4+getManhattanDistance(shelf1.getShelfGroup().getStartLatitude(),
				shelf2.getLatitude(),
				shelf1.getShelfGroup().getStartLongitude(),
				shelf2.getShelfGroup().getEndLongitude());
		Double V3 = P2+P4+getManhattanDistance(shelf1.getShelfGroup().getStartLatitude(),
				shelf2.getLatitude(),
				shelf1.getShelfGroup().getEndLongitude(),
				shelf2.getShelfGroup().getEndLongitude());
		Double V4 = P2+P3+getManhattanDistance(shelf1.getShelfGroup().getStartLatitude(),
				shelf2.getLatitude(),
				shelf1.getShelfGroup().getEndLongitude(),
				shelf2.getShelfGroup().getStartLongitude());
		return Math.min(V1, Math.min(V2, Math.min(V3, V4)));
	}
	
	private static Double getManhattanDistance(double x1, double x2, double y1, double y2) {
		return Math.abs((x1-x2)+(y1-y2));
	}

}
