package com.walmart.warehouse.utility;

import java.util.*;

import javax.management.Query;

import com.walmart.warehouse.domain.ShelfDO;

public class UltilityClass {

	class Pair
	{
		int x,y;
		Pair(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	int floorPlan[][]= new int[100][100];
	int vis[][]= new int[100][100];
	void updateFloorPlan()
	{
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
				floorPlan[i][j]=-1;
		}
		for(int i=2;i<=96;i+=4)
		{
			for(int j=2;j<96;j+=12)
				for(int k=0;k<10;k++)
				{
					floorPlan[i][j+k]=0;
					floorPlan[i+1][j+k]=0;
				}
		}
	}
	public int[][] getMinimumDistanceMatrix(Double[][] points) {
		updateFloorPlan();
		int finalDistanceMatrix[][] = new int[points[0].length][points[0].length];
		for(int i=0;i<points[0].length;i++)
		{
			bfs(points[0][i].intValue(),points[1][i].intValue());
			for(int j=0;j<points[0].length;j++)
			{
				finalDistanceMatrix[i][j]=vis[points[0][j].intValue()][points[1][j].intValue()];
			}
		}
		return finalDistanceMatrix;
	}
	Boolean check(int x, int y)
	{
		if (x>=0 && y>=0 && x<100 && y<100 && floorPlan[x][y] != 0){
			return true;
		}
		return false;
	}
	void bfs(int x,int y)
	{
		LinkedList<Pair> queueList = new LinkedList<UltilityClass.Pair>();
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
				vis[i][j]=-1;
		}
		queueList.add(new Pair(x, y));
		vis[x][y] = 1;
		int direction[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};

		while(queueList.size()!=0)
		{
			Pair obj = queueList.poll();
			int a = obj.getX();
			int b = obj.getY();
			for(int i=0;i<8;i++) {
				int x1 = a + direction[i][0];
				int y1 = b + direction[i][1];
				if( check(x1, y1)){
					if(vis[x1][y1]>0) {
						vis[x1][y1] = Math.min(vis[x1][y1], vis[a][b]+1);
					}
					else {
						queueList.add(new Pair(x1, y1));
						vis[x1][y1] = vis[a][b]+1;
					}
				}
			}
			
		}
	}
	
	
	
	
	
	
	
	/*public static Double findDistanceBetweenTwoShelves(ShelfDO shelf1, ShelfDO shelf2) {
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
	}*/

}
