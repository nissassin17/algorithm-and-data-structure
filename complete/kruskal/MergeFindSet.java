import java.util.HashMap;
import java.util.List;


// �e�ւ̃|�C���^���������\��
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

	// pointer �̕t���ւ��@O(1)	���������̖؂�傫���ق��̖؂̍����ւȂ���
	void merge(Set set0, Set set1){
		if (set0.size > set1.size)
			set1.root.parent = set0.root;
		else
			set0.root.parent = set1.root;
			
	}
	// �؂������̂ڂ�@O(log n);		// �o�H�̈��k�͖�����
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
