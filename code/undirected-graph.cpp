#include "undirected-graph.h"
namespace graph{
	UnDirectedGraph::UnDirectedGraph(vector<const vector<int>> const& inputEdge){
		vector<vector<int> edge(inputEdge);
		for (int i = 0; i < inputEdge.size(); ++i) {
			edge.push_back(vector<int>(inputEdge.size(), false));
		}
		for ( int i = 0; i < edge.size(); ++i) {
			for ( int j = 0; j < edge.size(); ++j) {
				edge[i][j] = edge[j][i] = inputEdge[i][j] || inputEdge[j][i];
			}
		}
		setEdge(edge);
	}
	vector<int> UnDirectedGraph::getCutPoints() const{
		vector<int> low(size(), -1);
		vector<int> num(size(), -1);
		vector<int> nChild(size(), 0);
		vector<int> ret;
		vector<int> isRoot(size(), false);
		int currentNum;
		for(int i = 0 ;i < size(); i++)
			if (num[i] == -1){
				visitCutPoint(-1, i, low, num, nChild, currentNum);
				isRoot[i] = true;
			}

		//get result
		//low == num and (not root OR nChild > 1)
		for(int i = 0 ; i < size(); i++)
			if (low[i] == num[i] && (!isRoot[i] || nChild[i] > 1))
				ret.push_back(i);
		
		//return
		return ret;
	}
	void UnDirectedGraph:: visitCutPoint(int parent,int x,vector<int>& low,vector<int>& num,vector<int>& nChild,int& currentNum) const{
		low[x] = num[x] = currentNum;
		currentNum++;
		for(int i = 0; i < size(); i++)
			if (num[i] == -1){
				nChild[i]++;
				visitCutPoint(x, i, low, num, nChild, currentNum);
			}else
			if (i != parent){
			low[x] = min(low[x], num[i]);
			}
	}
	UnDirectedGraph::~UnDirectedGraph(){}
}
