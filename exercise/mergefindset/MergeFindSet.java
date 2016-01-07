import java.util.HashMap;
import java.util.List;


// 親へのポインタをつかった表現
public class MergeFindSet {

	HashMap object_to_node = new HashMap();
	
	// initialize　(正しいコード)
	//　すべてのノードがそれぞれからなるばらばらの集合に属する
	MergeFindSet(List objects){
		for(Object object : objects){
			Node node = new Node(object);
			node.parent = new Set(node);
			object_to_node.put(object, node);
		}
	}

	// mergeの操作
	// pointer の付け替え　O(1)	小さい方の木を大きいほうの木の根元へつなげる
	void merge(Set set0, Set set1){
		//
		//　ここに正しい処理を書くこと
		//
	}
	
	// find の操作
	// 木をさかのぼって根にある集合を返す O(log n);		// 経路の圧縮は未実装
	Set find(Object object){
		//
		//　ここに正しい処理を書くこと
		//
		
		// 以下はダミーのコード
		Node node = (Node) object_to_node.get(object);
		return (Set) node.parent;
	}
	
}

// ノードの定義(正しいコード)
class Node {
	Object parent;
	Object object;
	Node(Object object){
		this.object = object;
	}
}

// 集合の定義　（正しいコード）
class Set {
	Set(Node root){
		this.root = root;
	}
	Node root;
	int size = 1;
}
/*

public class MergeFindSet {

	HashMap object_to_node = new HashMap();
	
	// initialize
	MergeFindSet(List objects){
		for(Object object : objects){
			Node node = new Node(object);
			node.parent = new Set(node);
			object_to_node.put(object, node);
		}
	}

	// pointer の付け替え　O(1)	小さい方の木を大きいほうの木の根元へつなげる
	void merge(Set set0, Set set1){
		if (set0 == set1)
			return;
		if (set0.size > set1.size){
			set1.root.parent = set0.root;
			set0.size += set1.size;
		}
		else {
			set0.root.parent = set1.root;
			set1.size += set0.size;
		}	
	}
	// 木をさかのぼる　O(log n);		// 経路の圧縮は未実装
	Set find(Object object){
		Node node = (Node) object_to_node.get(object);
		while(node.parent instanceof Node){
			node = (Node) node.parent;
		}
		return (Set) node.parent;
	}
}


*/