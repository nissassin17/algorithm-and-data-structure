import java.util.*;

public class BinarySearchTree {
	
	// �O����̃G���g���[�|�C���g�@(�������R�[�h)
	void insert(int object){
		if (root == null)
			root = new Node(object);
		else
			insert(root, object);
	}
	void delete(int object){
		root = delete(root, object);
	}
	Node root = null;
	
	
	
	
	
	// �ċA�I�Ɏq�����ǂ��ēK�؂ȏꏊ�ɓ����
	void insert(Node node, int object){

		// �ȉ��̓_�~�[�̃R�[�h�@�Ђ����獶�̎q�����ǂ��ċ󂢂Ă���t��������B
		if (node.left != null)
			insert(node.left, object);
		else
			node.left = new Node(object);
		
	}

	// �폜�̃R�[�h�B�ċA�I�ɍ~��Ă����ĖړI�̏ꏊ�������ăm�[�h���폜����B�폜������ɂ� deletemin�̌��ʂ𖄂ߍ��ށB
	Node delete(Node node, int object){

		// �ȉ��̓_�~�[�̃R�[�h�@�Ђ����獶�̎q�����ǂ��Ă����Č���������폜����
		if (node == null)
			return null;
		else if (node.object == object)
			return node.left;
		else {
			node.left = delete(node.left, object);
			return node;
		}
	}
	
	//�q���̂����̍ŏ��l���Ƃ��Ă���
	Node deletemin(Node node, Node parent){	

		// �ȉ��̓_�~�[�̃R�[�h�@�Ђ����獶�̎q�����ǂ��Ă����Đ�[��Ԃ�
		if (node == null)
			return null;
		else if (node.left == null){
			parent.left = null;
			return node;
		}
		else
			return deletemin(node.left, node);
		
	}	


}


// Node �̒�`�@(�������R�[�h)
class Node {
	Node left;
	Node right;
	int object;
	Node(int _object){
		object = _object;
	}
}


/*
 * �𓚗�
 
public class BinarySearchTree {
	void insert(int object){
		if (root == null)
			root = new Node(object);
		else
			insert(root, object);
	}
	void delete(int object){
		root = delete(root, object);
	}
	Node root = null;
	
	
	void insert(Node node, int object){
		if (object < node.object){
			if (node.left == null)
				node.left = new Node(object); //�V�����m�[�h��ǉ�
			else
				insert(node.left, object);	//���̎q��H��
		}
		else {
			if (node.right == null)
			   node.right = new Node(object);  //�V�����m�[�h��ǉ�
			else
			  insert(node.right, object); 	//�E�̎q��H��
		}
	}

	// �폜�̃R�[�h�B�ċA�I�ɍ~��Ă����ĖړI�̏ꏊ�������ăm�[�h���폜����B�폜������ɂ� deletemin�̌��ʂ𖄂ߍ��ށB
	Node delete(Node node, int object){
		if (node == null)
			return null;
		if (object < node.object){
			node.left = delete(node.left, object);  //���̎q��H��
			return node;
		}
		else if (object > node.object){
			node.right =  delete(node.right, object); //�E�̎q��H��
			return node;
		}
		else {	// ����node���폜����
			if (node.left == null && node.right == null)	//�@�q�����Ȃ�
				return null;
			else if (node.left == null && node.right != null)  // �E�̎q�̂�
				return node.right;
			else if (node.left != null && node.right == null)  // �E�̎q�̂�
				return node.left;
			else { 					//�q���Q��
				Node min = deletemin(node.right, node);	//�E�̎q���̍ŏ��l
				min.right = node.right;
				min.left = node.left;
				return min;
			}
		}
	}
	Node deletemin(Node node, Node parent){	//�q���̂����̍ŏ��l���Ƃ��Ă���
		if (node.left == null){	// �E�ɂ����q���Ȃ�
			if (parent.left == node)	// �������g�̂����ꏊ�ɉE�̎q��z�u����
				parent.left = node.right;
			else 
				parent.right = node.right;
			return node;	//�������g���ŏ��l
		}
		parent = node;
		node = node.left;
		while(node.left != null){	//���̎q��H��
			parent =node;
			node = node.left;
		}
		parent.left = node.right;
		return node;
	}	
}
*/