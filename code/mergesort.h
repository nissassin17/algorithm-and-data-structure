#ifndef MERGESORT_H
#define MERGESORT_H
#include <vector>
#include "sort.h"
using namespace std;
namespace sort{
	class MergeSort : public Sort{
		public:
			MergeSort(vector<int> const& a);
			virtual vector<int> sort()const;
		private:
			void sort_(vector<int> &a, int start, int finish) const;
			std::vector<int> data;
	};
}
#endif
