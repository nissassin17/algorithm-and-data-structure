#include <vector>
#include <cstdlib>
#include "sort.h"
#include "quicksort.h"
using namespace std;

namespace sort{
	void Quicksort::sort_(int *l, int *h) const{
		if (l >= h)
			return;
		int pivot = *(l + rand()% (h - l + 1));
		int *i(l), *j(h);
		do{
			while(*i < pivot) i++;
			while(*j > pivot) j--;
			if (i <= j){
				swap(*i, *j);
				i++;j--;
			}
		} while (i <= j);
		sort_(l, j);
		sort_(i, h);
	}

	vector<int> Quicksort::sort() const{
		if (data.empty())
			return data;
		vector<int> b(data);
		sort_(&b[0], &b[b.size() - 1]);
		return b;
	}
}
