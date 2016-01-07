import java.util.*;

public class Kruskal {
	
	// クラスカルのアルゴリズムで最小木を求める
	// 入力　G.edges の要素edge のコスト edge.cost
	// 出力　最小木に属する edgeの集合
	
	static List<Edge> compute_minimum_spanning_tree(UndirectedGraph G){
		
		// 準備　(正しいコード)

		// 最小木の準備
		List<Edge> mst = new ArrayList<Edge>();
		
		//優先度付待ち行列Uを用意し、すべての辺を挿入する
		Heap U = new Heap();									
		for(Edge edge: G.edges)
			U.insert(edge);
		
		//　集合群の準備
		MergeFindSet mfset = new MergeFindSet(G.vertices);	// MergeFindSetを用意する
		
		
		// 以下、ダミーのコード　単に順に取り出して加えていく
		for(int i=0; i<G.edges.size(); i++){
			Edge edge = U.deletemin();				// Uからコストが最小の辺{i,j}を取り出す			
		    mst.add(edge);
			Main.print(G, mst, edge);				// 表示
		}
		
		return mst;
	}
	
}

/*
public class Kruskal {
	
	// クラスカルのアルゴリズムで最小木を求める
	// 入力　G.edges の要素edge のコスト edge.cost
	// 出力　最小木に属する edgeの集合
	
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


*/