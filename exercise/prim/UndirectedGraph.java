import java.util.*;

public class UndirectedGraph {
	int n;				// number of vertices;
	int[][] d;			// edge costs
	int[][] positions;	// vertex positions
	int[][] edges;
	
	//List<Vertex> vertices = new ArrayList<Vertex>();
	//List<Edge> edges = new ArrayList<Edge>();
	
	UndirectedGraph(int n, int m){
		this.n = n;
		d = new int[n][n];
		positions = new int[n][2];
		edges = new int[m][2];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				d[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
	}
	
}
/*
class Vertex {
	int index;
	List<Edge> edges = new ArrayList();

	int[] position;
}

class Edge {
	Vertex start;
	Vertex end;
	int cost;
}
*/