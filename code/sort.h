#ifndef SORT_H
#define SORT_H
#include <vector>
using namespace std;
namespace sort{
	class Sort{
		public:
			virtual vector<int> sort() const = 0;
			virtual ~Sort();
	};
}
#endif
