#include <iostream>
#include <vector>
#include "array-tree.h"
using namespace std;
namespace tree{

	ArrayTree::ArrayTree(){
	}

	void ArrayTree::insert(int key){
		data.push_back(key);
	}

	bool ArrayTree::find(int key) const{
		for(auto it : data)
			if (key == it)
				return true;
		return false;
	}

	void ArrayTree::remove(int key){
		for(auto it = data.begin(); it != data.end(); it++)
			if (*it == key){
				data.erase(it);
				break;
			}
	}

	vector<int> ArrayTree::toArray() const{
		vector<int> ret(data);
		sort(ret.begin(), ret.end());
		return ret;
	}
}