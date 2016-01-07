import java.util.*;

public class Prim {
	static List<int[]> compute_minimum_spanning_tree(UndirectedGraph G){
		
		// 準備
		int n = G.n;					//　頂点数	
		int[][] d = G.d;				// 距離行列
		
		int s = 0;						//	s ← Vの任意の節点	
		
		int[] C = new int[n];			// C[v] ← d[s, v];	// 構築中の木と未処理節点vの間のコスト
		for(int i=0; i<n; i++)
			C[i] = d[s][i];
				
		int[] N = new int[n];			// N[v] ← s;	// vに最も近い構築中の木の中の節点
		for(int i=0; i<n; i++)
			N[i] = s;
		
		IntSet U = new IntSet();		//U ← V-s		//未処理節点の集合
		for(int i=1; i<n; i++)
			U.add(i);
			
		List<int[]> T = new ArrayList<int[]>();	//	T ← s;		//コスト最小の極大木
		
		while(U.size()>0){
			int w = -1;						// w ← ＵのうちC[w] が最小となるもの
			int min = Integer.MAX_VALUE;
			for(int i=0; i<U.size(); i++){
				int v = U.get(i);
				if (C[v] < min){
					min = C[v];
					w = v;
				}
			}
			
			U.remove(w);					//	Uからwを取り除く
			
			int[] edge = { w, N[w] };
			T.add( edge );					// T に辺{w, N[w]}を加える
			
			Main.print(G, T, C, w);			// 表示
			
			for (int i=0; i<U.size(); i++ ) {
			   int v = U.get(i);
			   if ( d[w][v] < C[v]){
				   C[v] = d[w][v]; 
				   N[v] = w; 				//更新
			   }
			}
		}
		return T;
	}
}
