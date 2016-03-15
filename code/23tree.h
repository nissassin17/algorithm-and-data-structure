#ifndef TWO_THREE_TREE_H
#define TWO_THREE_TREE_H
#include <vector>
#include "tree.h"
namespace tree{

	class Node{
		public:
			std::vector<Node *>child;
			int key;
			const int MAX_VAL = -1;
			bool isLeaf() const{
				return child.empty();
			}
			Node(int key){//init leaf
				this->key = key;
			}
			Node(Node *left = NULL, Node *middle = NULL, Node *right = NULL){//init not leaf
				key = -1;
				child.push_back(left);
				child.push_back(middle);
				if (right != NULL)
					child.push_back(right);
				for(auto it : child)
					if (key == -1 || key > it->key)
						key = it->key;
			}
			~Node(){
				for(auto it: child)
					delete it;
			}
			bool isSemileaf() const{
				return !isLeaf() && child[0]->isLeaf();
			}
			int findChildNo(int key){
				for(int i = 1 ; i < child.size(); i++)
					if (key < child[i]->key)
						return i - 1;
				return child.size() - 1;
			}
	};
	class TwoThreeTree : public Tree {
		private:
			Node *root;
			bool find_(Node *node, int key) const;
			void toArray_(std::vector<int>&, Node*node) const;
			Node *insert_(Node *node, int key);
			bool remove_(Node* node, int key);
		public:
			TwoThreeTree();
			void insert(int key);
			void remove(int key);
			bool find(int key) const;
			std::vector<int> toArray() const;
			virtual ~TwoThreeTree();
	};
}
#endif