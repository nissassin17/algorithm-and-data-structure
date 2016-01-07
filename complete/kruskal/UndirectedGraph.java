import java.util.*;

public class UndirectedGraph {
	
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
	
	UndirectedGraph(){
	}
	
}
class Vertex {
	int index;
	int[] position;
}

class Edge {
	Vertex start;
	Vertex end;
	int cost;
}

