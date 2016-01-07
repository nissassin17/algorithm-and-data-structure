import java.util.*;

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
				node.left = new Node(object); //新しいノードを追加
			else
				insert(node.left, object);	//左の子を辿る
		}
		else {
			if (node.right == null)
			   node.right = new Node(object);  //新しいノードを追加
			else
			  insert(node.right, object); 	//右の子を辿る
		}
	}

	// 削除のコード。再帰的に降りていって目的の場所を見つけてノードを削除する。削除した後には deleteminの結果を埋め込む。
	Node delete(Node node, int object){
		if (node == null)
			return null;
		if (object < node.object){
			node.left = delete(node.left, object);  //左の子を辿る
			return node;
		}
		else if (object > node.object){
			node.right =  delete(node.right, object); //右の子を辿る
			return node;
		}
		else {	// このnodeを削除する
			if (node.left == null && node.right == null)	//　子がいない
				return null;
			else if (node.left == null && node.right != null)  // 右の子のみ
				return node.right;
			else if (node.left != null && node.right == null)  // 右の子のみ
				return node.left;
			else { 					//子が２つ
				Node min = deletemin(node.right, node);	//右の子孫の最小値
				min.right = node.right;
				min.left = node.left;
				return min;
			}
		}
	}
	Node deletemin(Node node, Node parent){	//子孫のうちの最小値をとってくる
		if (node.left == null){	// 右にしか子がない
			if (parent.left == node)	// 自分自身のいた場所に右の子を配置する
				parent.left = node.right;
			else 
				parent.right = node.right;
			return node;	//自分自身が最小値
		}
		parent = node;
		node = node.left;
		while(node.left != null){	//左の子を辿る
			parent =node;
			node = node.left;
		}
		parent.left = node.right;
		return node;
	}	


}
class Node {
	Node left;
	Node right;
	int object;
	Node(int _object){
		object = _object;
	}
}
