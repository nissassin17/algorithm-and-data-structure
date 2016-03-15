OBJS = main.o array-tree.o 23tree.o tree.o quicksort.o mergesort.o graph.o undirected-graph.o
CC = g++
all: $(OBJS)
	$(CC) -std=c++11 $(OBJS)
obj: $(OBJS)

graph.o: graph.cpp
	$(CC) graph.cpp -c -std=c++11
undirected-graph.o: undirected-graph.cpp
	$(CC) undirected-graph.cpp -c -std=c++11
main.o: main.cpp kmp.cpp bm.cpp
	$(CC) main.cpp -c -std=c++11
array-tree.o: array-tree.cpp
	$(CC) array-tree.cpp -c -std=c++11
23tree.o: 23tree.cpp
	$(CC) 23tree.cpp -c -std=c++11
tree.o: tree.cpp
	$(CC) tree.cpp -c -std=c++11
quicksort.o: quicksort.cpp
	$(CC) quicksort.cpp -c -std=c++11
mergesort.o: mergesort.cpp
	$(CC) mergesort.cpp -c -std=c++11
clean:
	rm -rf $(OBJS)
