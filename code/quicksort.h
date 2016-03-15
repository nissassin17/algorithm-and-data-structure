#ifndef QUICKSORT_H
#define QUICKSORT_H
#include "sort.h"
#include <vector>
namespace sort{
class Quicksort: public Sort
{
public:
	Quicksort (vector<int>const& data);
	virtual vector<int> sort()const;

private:
	vector<int> data;
	void sort_(int *l, int *h) const;
};
}
#endif
