import java.util.HashMap;
import java.util.List;


// �e�ւ̃|�C���^���������\��
public class MergeFindSet {

	HashMap object_to_node = new HashMap();
	
	// initialize�@(�������R�[�h)
	//�@���ׂẴm�[�h�����ꂼ�ꂩ��Ȃ�΂�΂�̏W���ɑ�����
	MergeFindSet(List objects){
		for(Object object : objects){
			Node node = new Node(object);
			node.parent = new Set(node);
			object_to_node.put(object, node);
		}
	}

	// merge�̑���
	// pointer �̕t���ւ��@O(1)	���������̖؂�傫���ق��̖؂̍����ւȂ���
	void merge(Set set0, Set set1){
		//
		//�@�����ɐ�������������������
		//
	}
	
	// find �̑���
	// �؂������̂ڂ��č��ɂ���W����Ԃ� O(log n);		// �o�H�̈��k�͖�����
	Set find(Object object){
		//
		//�@�����ɐ�������������������
		//
		
		// �ȉ��̓_�~�[�̃R�[�h
		Node node = (Node) object_to_node.get(object);
		return (Set) node.parent;
	}
	
}

// �m�[�h�̒�`(�������R�[�h)
class Node {
	Object parent;
	Object object;
	Node(Object object){
		this.object = object;
	}
}

// �W���̒�`�@�i�������R�[�h�j
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

	// pointer �̕t���ւ��@O(1)	���������̖؂�傫���ق��̖؂̍����ւȂ���
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
	// �؂������̂ڂ�@O(log n);		// �o�H�̈��k�͖�����
	Set find(Object object){
		Node node = (Node) object_to_node.get(object);
		while(node.parent instanceof Node){
			node = (Node) node.parent;
		}
		return (Set) node.parent;
	}
}


*/