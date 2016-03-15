#ifndef TREE_H
#define TREE_H
#include <vector>
namespace tree{
	class Tree{
		public:
			enum Action { INSERT, REMOVE, FIND};
			virtual void insert(int key) = 0;
			virtual void remove(int key) = 0;
			virtual bool find(int key) const = 0;
			virtual std::vector<int> toArray() const = 0;

			virtual ~Tree() = 0;
	};
}

#endif
