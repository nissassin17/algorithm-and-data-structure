import java.util.*;

public class Kruskal {
	
	static List<Edge> compute_minimum_spanning_tree(UndirectedGraph G){
		
		List<Edge> mst = new ArrayList<Edge>();
		
		Heap U = new Heap();							//優先度付待ち行列Uを用意し、すべての辺を挿入する		
		for(Edge edge: G.edges)
			U.insert(edge);
		
		MergeFindSet mfset = new MergeFindSet(G.vertices);	// MergeFindSetを用意する
		
		while(mst.size() < G.vertices.size()-1){
			Edge edge = U.deletemin();				// Uからコストが最小の辺{i,j}を取り出す			
			Set Ti = mfset.find(edge.start);		// Ti ← iを含む部分木					
			Set Tj = mfset.find(edge.end);			// Tj ← jを含む部分木			
			if (Ti != Tj) {
			    mst.add(edge);
			    mfset.merge(Ti, Tj);				//TiとTiと辺{i,j}でつないだ部分木Tをつくる
			}
		    
			Main.print(G, mst, edge);				// 表示
		}
		return mst;
	}
	
}

