package com.walmart.warehouse.utility;

import java.util.*;
import com.walmart.warehouse.utility.UltilityClass;

import com.walmart.warehouse.domain.ShelfDO;
public class Graph 
{ 
    // A class to represent a graph edge 
    static LinkedList<Integer> resultList = new LinkedList<Integer>();
	class Edge implements Comparable<Edge> 
    { 
        int src, dest; 
        int weight; 
  
        // Comparator function used for sorting edges  
        // based on their weight 
        public int compareTo(Edge compareEdge) 
        { 
            return this.weight-compareEdge.weight; 
        }
    }; 
  
    // A class to represent a subset for union-find 
    class subset 
    { 
        int parent, rank; 
    }; 
  
    int V, E;    // V-> no. of vertices & E->no.of edges 
    Edge edge[]; // collection of all edges 
  
    // Creates a graph with V vertices and E edges 
    public Graph(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
    } 
  
    // A utility function to find set of an element i 
    // (uses path compression technique) 
    int find(subset subsets[], int i) 
    { 
        // find root and make root as parent of i (path compression) 
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
  
    // A function that does union of two sets of x and y 
    // (uses union by rank) 
    void Union(subset subsets[], int x, int y) 
    { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 
  
        // Attach smaller rank tree under root of high rank tree 
        // (Union by Rank) 
        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[xroot].rank > subsets[yroot].rank) 
            subsets[yroot].parent = xroot; 
  
        // If ranks are same, then make one as root and increment 
        // its rank by one 
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
    } 
  
    // The main function to construct MST using Kruskal's algorithm 
    Edge[] KruskalMST() 
    { 
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST 
        int e = 0;  // An index variable, used for result[] 
        int i = 0;  // An index variable, used for sorted edges 
        for (i=0; i<V; ++i) 
            result[i] = new Edge(); 
  
        // Step 1:  Sort all the edges in non-decreasing order of their 
        // weight.  If we are not allowed to change the given graph, we 
        // can create a copy of array of edges 
        Arrays.sort(edge); 
  
        // Allocate memory for creating V ssubsets 
        subset subsets[] = new subset[V]; 
        for(i=0; i<V; ++i) 
            subsets[i]=new subset(); 
  
        // Create V subsets with single elements 
        for (int v = 0; v < V; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 
  
        i = 0;  // Index used to pick next edge 
  
        // Number of edges to be taken is equal to V-1 
        while (e < V - 1) 
        { 
            // Step 2: Pick the smallest edge. And increment  
            // the index for next iteration 
            Edge next_edge = new Edge(); 
            next_edge = edge[i++]; 
  
            int x = find(subsets, next_edge.src); 
            int y = find(subsets, next_edge.dest); 
  
            // If including this edge does't cause cycle, 
            // include it in result and increment the index  
            // of result for next edge 
            if (x != y) 
            { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 
            // Else discard the next_edge 
        } 
  
        // print the contents of result[] to display 
        // the built MST 
        /*System.out.println("Following are the edges in " +  
                                     "the constructed MST"); 
        for (i = 0; i < e; ++i) 
            System.out.println(result[i].src+" -- " +  
                   result[i].dest+" == " + result[i].weight);*/
        return result;
    }
    public void dfs(LinkedList<Integer> adjList[], int vis[],int i)
    {
    	vis[i]=1;
    	resultList.add(i);
    	for(int k=0;k<adjList[i].size();k++)
    	{
    		if(vis[adjList[i].get(k)]==0)
    			dfs(adjList, vis, adjList[i].get(k));
    		
    	}
    }
    public List<String> calcDistance(Set<ShelfDO> setofShelves)
    {
    	List<ShelfDO> shelves= new ArrayList<ShelfDO>();
    	int vertices = setofShelves.size();
    	int edges = (vertices*(vertices-1))/2,k=0;
    	Double points[][] = new Double[2][vertices];
    	for(ShelfDO shelfDO: setofShelves)
    	{
    		shelves.add(shelfDO);
    		System.out.println(shelfDO.getShelfKey());
    		points[0][k]=(shelfDO.getLongitude()%2==0)?(shelfDO.getLongitude()-1):(shelfDO.getLongitude()+1);
    		points[1][k++]= shelfDO.getLatitude();
    	}
//    	points[0][0]=1.0;
//		points[1][0]= 10.0;
//		points[0][1]=4.0;
//		points[1][1]= 6.0;
//		points[0][2]=4.0;
//		points[1][2]= 11.0;
    	k=0;
    	Graph graph = new Graph(vertices, edges);
    	UltilityClass objectUltilityClass = new UltilityClass();
    	int distance[][] = new int[vertices][vertices];
    	distance = objectUltilityClass.getMinimumDistanceMatrix(points);
//    	for(int i=0;i<3;i++)
//    	{
//    		for(int j=0;j<3;j++)
//    			System.out.print(distance[i][j] +" ");
//    		System.out.println();
//    	}
    	for(int i=0;i<vertices-1;i++)
    	{
    		for(int j=i+1;j<vertices;j++)
    		{
    			graph.edge[k].src=i;
    			graph.edge[k].dest=j;
    			graph.edge[k++].weight=distance[i][j];
    		}
    	}
    	
    	Edge result[];
    	result = graph.KruskalMST();
    	LinkedList<Integer> adjList[] = new LinkedList[vertices];
    	for ( int i=0;i<vertices;i++)
    		adjList[i]= new LinkedList<Integer>(); 
    	for(int i=0;i<result.length;i++)
    	{
    		adjList[result[i].src].add(result[i].dest);
    		adjList[result[i].dest].add(result[i].src);
    	}
    	int visited[]=new int[vertices];
    	Arrays.fill(visited, 0);
    	resultList.clear();
    	dfs(adjList,visited,0);
//    	for(int i=0;i<resultList.size();i++)
//    		System.out.print(resultList.get(i)+" ");
//    	System.out.println();
    	List<String> myRouteList = new ArrayList<String>();
    	for(int i=0;i<resultList.size();i++)
    	{
    		myRouteList.add(
    				shelves.get(
    						resultList.get(i)).
    							getShelfName());
    	}
    	return myRouteList;
    }
}
  
    // Driver Program 
  
