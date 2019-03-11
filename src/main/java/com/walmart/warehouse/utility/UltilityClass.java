package com.walmart.warehouse.utility;

import com.walmart.warehouse.domain.ShelfDO;

public class UltilityClass {
	
	public static Double findDistanceBetweenTwoShelves(ShelfDO shelf1, ShelfDO shelf2) {
		if(shelf1 == null || shelf2 == null)
			return 0.0;
		Double a1 = shelf1.getLatitude() - shelf1.getShelfGroup().getStartLatitude();
		Double a2 = shelf1.getLatitude() - shelf1.getShelfGroup().getEndLatitude();
		Double c1 = shelf2.getLatitude() - shelf2.getShelfGroup().getStartLatitude();
		Double c2 = shelf2.getLatitude() - shelf2.getShelfGroup().getEndLatitude();
		Double b1 = shelf1.getLongitude() - shelf1.getShelfGroup().getStartLongitude();
		Double b2 = shelf1.getLongitude() - shelf1.getShelfGroup().getEndLongitude();
		Double d1 = shelf2.getLongitude() - shelf2.getShelfGroup().getStartLongitude();
		Double d2 = shelf2.getLongitude() - shelf2.getShelfGroup().getEndLongitude();
		Double shelf1path1Latitude = shelf1.getLatitude()+a1;
		Double shelf1path2Latitude = shelf1.getLatitude()+a2;
		Double shelf1path1Longitude = shelf1.getLongitude()+b1;
		Double shelf1path2Longitude = shelf1.getLongitude()+b2;
		Double shelf2path1Latitude = shelf2.getLatitude()+c1;
		Double shelf2path2Latitude = shelf2.getLatitude()+c2;
		Double shelf2path1Longitude = shelf2.getLongitude()+d1;
		Double shelf2path2Longitude = shelf2.getLongitude()+d2;
		Double e1,e2,e3,e4;
		e1 = getManhattanDistance(shelf1path1Latitude, shelf1path1Longitude, shelf2path1Latitude, shelf2path1Longitude)+Math.abs(a1)+Math.abs(b1)+Math.abs(c1)+Math.abs(d1);
		e2 = getManhattanDistance(shelf1path1Latitude, shelf1path1Longitude, shelf2path2Latitude, shelf2path2Longitude)+Math.abs(a1)+Math.abs(b1)+Math.abs(c2)+Math.abs(d2);
		e3 = getManhattanDistance(shelf1path2Latitude, shelf1path2Longitude, shelf2path2Latitude, shelf2path2Longitude)+Math.abs(a2)+Math.abs(b2)+Math.abs(c2)+Math.abs(d2);
		e4 = getManhattanDistance(shelf1path2Latitude, shelf1path2Longitude, shelf2path1Latitude, shelf2path1Longitude)+Math.abs(a2)+Math.abs(b2)+Math.abs(c1)+Math.abs(d1);
		return Math.min(e1, Math.min(e2, Math.min(e3, e4)));
	}
	
	private static Double getManhattanDistance(double x1, double x2, double y1, double y2) {
		return Math.abs((x1-x2)+(y1-y2));
	}

}
