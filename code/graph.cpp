#include "graph.h"
namespace graph{
	Graph::Graph(vector<const vector<int>> const& edge){
		setEdge(edge);
	}
	Graph::~Graph(){}
	vector<vector<int>> Graph::getStrongConnectedComponent() const{
		vector<int> ord(edge.size(), -1);
		int currentOrd = 0;
		for (int i = 0; i < size(); ++i) {
			if (ord[i] == -1){
				visitSetOrd(ord, i, currentOrd);
			}
		}

		//list of nodes sorted by ord
		vector<int> list(size(), 0);
		for(int i = 0 ; i < size(); i++)
			list[ord[i]] = i;
		sort(list.begin(), list.end());

		//mark for final visit
		vector<bool> mark(size(), false);

		//for result
		vector<vector<int>> ret;
		for(int i = list.size() - 1; i >= 0; i--){
			vector<int> component;
			visitStrongComponent(list[list.size() - 1], mark, list, component);
			ret.push_back(component);
		}
		return ret;
	}

	void Graph::visitStrongComponent(int x, vector<bool> &mark, vector<int> const& list, vector<int> &component) const{
		mark[x] = true;
		component.push_back(x);
		for(int i = list.size() - 1; i >=0; i--)
			if (!mark[list[i]] && edge[list[i]][x]){
				visitStrongComponent(list[i], mark, list, component);
			}
	}
	void Graph::visitSetOrd(vector<int> &ord, int x, int &currentOrd)const{
		currentOrd++;
		ord[x] = 0;
		for(int i = 0; i < size(); i++)
			if (ord[i] == -1 && edge[x][i])
				visitSetOrd(ord, x, currentOrd);
		ord[x] = currentOrd;
	}
	void Graph::setEdge(vector<const vector<int>> const& edge){
		this->edge.clear();
		for(auto it : edge)
			this->edge.push_back(vector<int>(it.begin(), it.end()));
	}

	int Graph::size() const{
		return edge.size();
	}
}
