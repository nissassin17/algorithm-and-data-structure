#include "mergesort.h"
namespace sort{
	MergeSort::MergeSort(vector<int> const& v){
		this->data = v;
	}

	vector<int> MergeSort::sort()const{
		vector<int> b(data);
		sort_(b, 0, b.size());
		return b;
	}

	void MergeSort::sort_(vector<int> &a, int start, int finish) const{
		if (start + 1 >= finish)
			return;
		int middle = (start + finish) / 2 + 1;
		std::vector<int> b(a);
		sort_(b, start, middle);
		sort_(b, middle, finish);
		for(int i = start, j = middle, k = 0; i < middle && j < finish; k++){
			if (i == middle || (j < finish && b[j] < b[i])){
				a[k] = b[j];
				j++;
			}else {
				a[k] = b[i];
				i++;
			}
		}
	}
}
