#ifndef ARRAY_TREE_H
#define ARRAY_TREE_H
#include "tree.h"
#include <vector>
using namespace std;
namespace tree{
	class ArrayTree: public Tree{
		private:
			vector<int> data;
		public:
			ArrayTree();
			void insert(int key);
			void remove(int key);
			bool find(int key) const;
			vector<int> toArray() const;
	};
}

#endif