import java.util.HashMap;
import java.util.List;


// 親へのポインタをつかった表現
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
		if (set0.size > set1.size)
			set1.root.parent = set0.root;
		else
			set0.root.parent = set1.root;
			
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

class Node {
	Object parent;
	Object object;
	Node(Object object){
		this.object = object;
	}
}

class Set {
	Set(Node root){
		this.root = root;
	}
	Node root;
	int size = 0;
}
