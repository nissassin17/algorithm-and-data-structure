#include <string>
#include <cmath>
#include <algorithm>
using namespace std;
namespace string_test{
	class BM{
		private:
			vector<int> skip;
			string needles;
			void setNeedles(string needles){
				this->needles = needles;
				skip = vector<int>('z', needles.length());
				for(int i = 0; i < needles.length(); i++)
					skip[needles[i]] = needles.length() - i - 1;
			};
		public:
			BM(string needles){
				setNeedles(needles);
			}

			bool search(string haystack){
				int current = 0;
				int t = 0;
				while(current + needles.length() < haystack.length()){
					while(t < needles.length() && needles[t] == haystack[current + t])
						t++;
					if (t == needles.length())
						return true;
					current += std::max(1, skip[haystack[current + t]] - ((int)needles.length() - 1 - t));
				}
				return false;
			}
	};
}
