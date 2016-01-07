#include <iostream>
#include <cstdlib>
using namespace std;

void quicksort(int *array, int l, int h){
	if (l >= h)
		return;
	int pivot = array[rand()%(h - l + 1) + l];
	int i(l), j(h);
	while(i <= j){
		while(array[i] < pivot) i++;
		while(array[j] > pivot) j--;
		if (i <= j){
			int tmp = array[i]; array[i] = array[j]; array[j] = tmp;
			i++; j--;
		}
	}
	quicksort(array, l, j);
	quicksort(array, i, h);
}
void test(int n){
	int *data = new int[n];
	srand(static_cast<unsigned int>(time(NULL)));
	for(int i = 0; i < n; i++)
		data[i] = rand()%1000000;
	clock_t t = clock();
	quicksort(data, 0, n - 1);
	t = clock() - t;
	delete [] data;
	printf("Result with n = %d: %lf (millisecond)\n", n, (static_cast<float>(t) / CLOCKS_PER_SEC) * 1000);
}

int main(){
	test(1000000);
	test(2000000);
	test(4000000);
	test(8000000);
	return EXIT_SUCCESS;
}
