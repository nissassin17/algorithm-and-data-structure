#include <iostream>
#include <algorithm>
#include <ctime>
#include <cstdlib>
#include <vector>

using namespace std;

#define TEST_TREE
#define TEST_STRING
#define TEST_SORT
#define NMAX 10000
#define VAL_MAX 10

int gen(int val_max){
    return rand()%val_max;
}
int gen(int val_min, int val_max){
    return rand()%(val_max - val_min + 1) + val_min;
}

//test string
//{{{
#ifdef TEST_STRING
#include "kmp.cpp"
#include "bm.cpp"
using namespace string_test;

vector<pair<string, vector<string>>> stringTestSuite(){
	vector<pair<string, vector<string>>> ret;
	string hs[] = { "aabbcdd", "aabbcc", "xaabcd" };
	string needles = "aabb";
	vector<string> haystacks(hs, hs + 3);
	ret.push_back(make_pair(needles, haystacks));
	return ret;
}
bool testString(){
	for(auto pair : stringTestSuite()){
	KMP kmp(pair.first);
	BM bm(pair.first);
		//cout << "Needles: " << pair.first << endl;
		//for(auto haystack : pair.second)
			//cout << "\t" << haystack << ": " << (kmp.search(haystack) ? "found" : "not found" )<< endl;
		for(auto haystack : pair.second)
			if (kmp.search(haystack) != bm.search(haystack)){
				cout << "String search failed" << endl;
				cout << "Needle: " << pair.first << endl;
				cout << "Haystack: " << haystack << endl;
				cout << "KMP: " << (kmp.search(haystack) ? "found" : "not found") << endl;
				cout << "BM: " << (bm.search(haystack) ? "found" : "not found") << endl;
				return false;
			}
	}
	return true;
}
#endif
//}}}

//test sort
//{{{
#ifdef TEST_SORT
#include "quicksort.h"
#include "mergesort.h"
using namespace sort;

bool testSort(){
	vector<Sort*> sorts;
	vector<int> a;
	for(int i = 0; i < NMAX; i++){
		a.push_back(gen(VAL_MAX));
	}
	sorts.clear();
	sorts.push_back(new Quicksort(a));
	sorts.push_back(new MergeSort(a));

	vector<vector<int>> ret;
	for(auto it : sorts){
		ret.push_back(it->sort());
	}
	for(auto it : ret)
		for(auto jt : ret)
			if (it != jt)
				return false;
	
	//free
	for(auto it : sorts)
		delete it;
	return true;
}
#endif
//}}}


//Test tree
//{{{
#ifdef TEST_TREE
#include "array-tree.h"
#include "23tree.h"
using namespace tree;

void treeGen(vector<int> &acts, vector<int> &keys){
    int a[] = { 1, 1};
    int b[] = { 1, 0};
    for(int i = 0; i < NMAX; i++){
        acts.push_back(gen(3));
        keys.push_back(gen(VAL_MAX));
    }return;
    acts = vector<int>(a, a + 3);
    keys = vector<int>(b, b + 3);
}
bool testTree(){
	vector<Tree*> trees;
	trees.push_back(new ArrayTree());
	trees.push_back(new  TwoThreeTree());
    vector<int> acts, keys;
    treeGen(acts, keys);
    vector<bool> ret;

    for(int i = 0; i < acts.size(); i++){
		int act = acts[i];
        int key = keys[i];
        ret.clear();
		for(auto it : trees)
			switch(act){

				case 0: //insert
//                    cout << "insert " << key << endl;
					it->insert(key);
					break;
				case 1: //remove
//                    cout << "remove " << key << endl;
					it->remove(key);
					break;
				case 2: //find
//                    cout << "find " << key << ": " << (it->find(key) ? "found" : "not found") << endl;
                    ret.push_back(it->find(key));
					break;
				default:
					break;
			}
        for(auto it : ret)
            for(auto jt : ret)
                if (it != jt){
                    return false;
                }
	}
	for(auto it : trees)
		delete it;
	return true;
}
#endif
//}}}

void init(){
	srand((unsigned int)time(NULL));
}




int main(){
	init();
#ifdef TEST_TREE
	cout << "Test tree: " << flush;
	if (testTree())
		cout << "ok!" << endl;
	else
		cout << "failed" << endl;
#endif

#ifdef TEST_STRING
	cout << "Test string: " << endl;
	if(testString())
		cout << "ok!" << endl;
	else
		cout << "failed" << endl;
#endif

#ifdef TEST_SORT
	cout << "Test sort: " << flush;
	if (testSort())
		cout << "ok!" << endl;
	else
		cout << "failed" << endl;
#endif

	return 0;
}
