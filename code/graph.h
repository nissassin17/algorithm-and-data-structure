#ifndef GRAPH_H
#define GRAPH_H
#include <vector>
using namespace std;
namespace graph{
	class Graph
	{
	public:
		Graph (vector<const vector<int>> const& edge);
		virtual ~Graph ();
		vector<vector<int>> getStrongConnectedComponent() const;
	
	private:
		std::vector<vector<int>> edge;
		void setEdge(vector<const vector<int>> const& edge);
		int size() const;
		void visitSetOrd(vector<int> &ord, int x, int &currentOrd)const;
		void visitStrongComponent(int x, vector<bool> &mark, vector<int> const& list, vector<int> &component)const;

	};
}
#endif
