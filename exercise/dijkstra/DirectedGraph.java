import java.util.*;

public class DirectedGraph {
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
}

class Vertex {
	int cost;		// cost from node 0
	List<Edge> edges = new ArrayList();

	int[] position;
}

class Edge {
	Vertex start;
	Vertex end;
	int cost;
}