public class Dijkstra {

	// ダイクストラのアルゴリズムによって最短距離を計算する  
	// 入力データ graph.edges　の各要素 edge のコスト edge.cost
	// 出力データ start_vertexからgraph.vertices の各要素 v までの最短距離 v.cost
	
	static void dijkstra(DirectedGraph graph, Vertex start_vertex){

	    //　準備　(正しいコード)
		for(Vertex v : graph.vertices)	// 初期化。直接移動のコストをCにセットする。
	        v.cost = Integer.MAX_VALUE;
	    for(Edge edge : start_vertex.edges)
	    	edge.end.cost = edge.cost;
	    start_vertex.cost = 0;
	
	    Heap heap = new Heap();
	    for(Vertex v : graph.vertices)
	    	heap.insert(v);				// ヒープ S にＶのすべての要素vを加える。順序はC[v]を基準とする。

	    

	    // メインの計算部分
	    //　heapから順に取り出し、 cost を更新していく
	    
	    // 以下、ダミーのコード ただ単に heap から取り出していくだけ
	    while( !heap.is_empty() ){						
	       Vertex w = heap.deletemin();	// w ← SからC[w]が最小の頂点wを取り出す
	       Main.print(graph, w);		// 表示
	    }
	}
}

/*
解答例
public class Dijkstra {

	static void dijkstra(DirectedGraph graph, Vertex start_vertex){

	    for(Vertex v : graph.vertices)	// 初期化。直接移動のコストをCにセットする。
	        v.cost = Integer.MAX_VALUE;
	    for(Edge edge : start_vertex.edges)
	    	edge.end.cost = edge.cost;
	    start_vertex.cost = 0;
	    
	    Heap heap = new Heap();
	    for(Vertex v : graph.vertices)
	    	heap.insert(v);				// ヒープ S にＶのすべての要素vを加える。順序はC[v]を基準とする。
	    	
	    while( !heap.is_empty() ){						
	       Vertex w = heap.deletemin();	// w ← SからC[w]が最小の頂点wを取り出す
	       Main.print(graph, w);		// 表示
	       for(Edge edge : w.edges){	// v ← wから出ている辺の行き先)
	    	   Vertex v = edge.end;
	    	   if (w.cost == Integer.MAX_VALUE)
	    		   continue;						// 注意: MAXに足してしまうとオーバーフローする…
	    	   if ( v.cost > w.cost + edge.cost ){
	    		   v.cost = w.cost + edge.cost;	 	// コストの更新
	    		   heap.update(v);               	// ヒープにおけるvの位置をC[v]に従って繰り上げる （逆転がなくなるまで上に上げていく。）
	    	   }
	       }
	    }
	}

}

*/