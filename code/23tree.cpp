#include "23tree.h"
using namespace std;
namespace tree{
	TwoThreeTree::TwoThreeTree(){
		root = NULL;
	}
	bool TwoThreeTree::find(int key)const{
		return find_(root, key);
	}
	bool TwoThreeTree::find_(Node *node, int key) const{
		if (node == NULL)//only root
			return false;
		if (node->isLeaf())
			return node->key == key;
		return find_(node->child[node->findChildNo(key)], key);
	}

	TwoThreeTree::~TwoThreeTree(){
		if (root != NULL)
			delete root;
	}
	void TwoThreeTree::insert(int key){
		root = insert_(root, key);
	}

	//return inserted node, but does not have parent yet
	Node *TwoThreeTree::insert_(Node *node, int key){
		if (node == NULL)//root
			return new Node(key);
		if (node->isLeaf()){ //root
			return key < node->key ? new Node(new Node(key), node) : new Node(node, new Node(key));
		}
		if (node->isSemileaf()){
			node->child.insert(node->child.begin() + node->findChildNo(key) + 1, new Node(key));
			return node;
		}
		int toInsert = node->findChildNo(key);
		insert_(node->child[toInsert], key);
		if (node->child[toInsert]->child.size() == 4){
			node->child.insert(node->child.begin() + toInsert + 1, new Node(node->child[toInsert]->child[2], node->child[toInsert]->child[3]));
			node->child[toInsert]->child.pop_back();
			node->child[toInsert]->child.pop_back();
		}
		return node;
	}

	bool TwoThreeTree::remove_(Node *node, int key){
		if (node == NULL)
			return false;
		if (node->isLeaf()){
			if (node->key == key){
				delete node;
				return true;
			}
			return false;
		}
		int toRemove = node->findChildNo(key);
		if (remove_(node->child[toRemove], key)){
			node->child.erase(node->child.begin() + toRemove);
			return false;
		}

		if (node->child[toRemove]->child.size() == 1){
            int borrower = -1;
			if (toRemove > 0 && node->child[toRemove - 1]->child.size() == 3){
				borrower = toRemove - 1;
			}
			else {
				if (toRemove < node->child.size() - 1 && node->child[toRemove + 1]->child.size() == 3)
			borrower = toRemove + 1;
			}
		
		if (borrower != -1){
			node->child[toRemove]->child[1] = node->child[borrower]->child[2];
			node->child[borrower]->child.pop_back();
		}else{
			if (toRemove > 0)
				borrower = toRemove - 1;
			else
				borrower = toRemove + 1;
			auto tmp = *(node->child[toRemove]->child.rbegin());
			node->child[borrower]->child.push_back(tmp);
			node->child[toRemove]->child.pop_back();
			delete node->child[toRemove];
			node->child.erase(node->child.begin() + toRemove);
		}
        }
		return false;
	}

	void TwoThreeTree::remove(int key){
		if ( remove_(root, key))
			root = NULL;
		else if (root != NULL && root->child.size() == 1){
			auto t = root;
			root = root->child[0];
			t->child.clear();
			delete t;
		}
	}

	vector<int> TwoThreeTree::toArray() const{
		vector<int> ret;
		toArray_(ret, root);
		return ret;
	}

	void TwoThreeTree::toArray_(vector<int> &ret, Node *node) const{
		if (node == NULL)
			return;
		if (node->isLeaf())
			ret.push_back(node->key);
		for(int i = 0; i < 3; i++)
			toArray_(ret, node->child[ i ]);
	}

}
