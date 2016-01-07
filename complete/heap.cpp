#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;
class Heap{
	public:
		Heap(int nmax){
			data = new int[nmax + 1];
			size = 0;
		}
		~Heap(){
			delete [] data;
			size = 0;
		}

		void insert(int key){
			size++;
			int i;
			for(i = size; i > 1; i >>= 1)
				if (data[i >> 1] > key)
					data[i] = data[i >> 1];
				else
					break;
			data[i] = key;
		}

		int deletemin(){
			if (size == 0)
				return 0;
			int ret = data[1];
			size--;
			int i, j;
			for(i = 1, j = 2; j <= size; i = j, j = i << 1){
				if (j < size and data[j] > data[j + 1])
					j++;
				if (data[size + 1] > data[j])
					data[i] = data[j];
				else
					break;
			}
			data[i] = data[size + 1];
			return ret;
		}

	private:
		int *data;
		int size;
};

void test(int n){
	clock_t t = clock();
	Heap heap(n);
	for(int i = 0; i < n; i++)
		heap.insert(rand()%1000000);
	for(int i = 0; i < n; i++)
		heap.deletemin();
		//printf("%d\n", heap.deletemin());
	//printf("Result with n = %d: %ld (millisecond)\n", n, static_cast<long int>(time(NULL)) - static_cast<long int>(t));
	t = clock() - t;
	printf("Result with n = %d: %lf (millisecond)\n", n, (static_cast<float>(t) / CLOCKS_PER_SEC) * 1000);
}
int main(){
	test(1000000);
	test(2000000);
	test(4000000);
	test(8000000);
	return EXIT_SUCCESS;
}
