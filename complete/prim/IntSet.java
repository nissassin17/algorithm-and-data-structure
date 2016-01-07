import java.util.*;

public class IntSet {
	List<Integer> data = new ArrayList<Integer>();
	void add(int a){
		data.add(new Integer(a));
	}
	void remove(int a){
		for(int i=0; i<data.size(); i++){
			if (data.get(i).intValue() == a){
				data.remove(i);
				break;
			}
		}
	}
	int get(int i){
		return data.get(i).intValue();
	}
	int size(){
		return data.size();
	}
	
}
