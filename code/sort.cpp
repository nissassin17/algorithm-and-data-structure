#include <algorithm>
#include <vector>
#include <iostream>
#include <cstdlib>
#include <ctime>

#include "quicksort.cpp"

#define NMAX 1000
#define VMAX 100000001

using namespace std;

struct Generator{
	int vmax;
	Generator(int vmax) : vmax(vmax){}
	int operator()(){return rand()%vmax;}
};

vector<int> gen(int n, int vmax){
	srand((unsigned int)time(NULL));
	vector<int> a(n);
	generate(a.begin(), a.end(), Generator(vmax));
	return a;
}

//int main(){
//	vector<int> a(gen(NMAX, VMAX));
//	vector<int> b(quicksort(a));
//	vector<int> c(a); sort(c.begin(), c.end());
//	cout << "Quicksort: " << (c == b ? "correct" : "wrong") << endl;
//
//	return EXIT_SUCCESS;
//}
