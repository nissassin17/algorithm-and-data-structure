#include <string>
using namespace std;
namespace string_test{
	class KMP{
		private:
			vector<int> kmp;
			string needles;
			void setNeedles(string needles){
				this->needles = needles;

				kmp.clear();
				for(int i = 0;i  < needles.length(); i++){
					if (i == 0)
						kmp.push_back(-1);
					else{
						int t;
						for(t = kmp[i - 1]; t != -1 && needles[t + 1] != needles[i]; t = kmp[t]){
						}
						if (needles[t + 1] == needles[i])
							kmp.push_back(t + 1);
						else
							kmp.push_back(-1);
					}
				}
			}
		public:
			KMP(string needles){
				setNeedles(needles);
			}

			bool search(string haystack){
				int current = -1;
				for(int i = 0; i < haystack.length(); i++){
					for(; current != -1 && haystack[i] != needles[current + 1]; current = kmp[current])
						;
					if (haystack[i] == needles[current + 1])
						current ++;
					if (current == needles.length() - 1)
						return true;
				}
				return false;
			}
	};
};
