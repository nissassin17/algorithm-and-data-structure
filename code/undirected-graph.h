#ifndef DIRECTED_GRAPH_H
#define DIRECTED_GRAPH_H
#include <vector>
using namespace std;
#include "graph.h"
namespace graph{
	class UnDirectedGraph : public Graph
	{
	public:
		UnDirectedGraph (vector<const vector<int>> const& edge);
		virtual ~UnDirectedGraph ();
		vector<int> getCutPoints() const;
	
	private:
	void  visitCutPoint(int parent,int x,vector<int>& low,vector<int>& num,vector<int>& nChild,int& currentNum)const;
	};
}

#endif
