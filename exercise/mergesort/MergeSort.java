import java.util.*;
public class MergeSort {

	// マージソートの本体  (正しいコード)
	// 前半分、後ろ半分をソートしてそれをmergeする
	public static List mergesort(List array){
		int n = array.size();
		if (n <= 1)											// 再帰呼び出しの末端
			return array;
		
		Main.print(array, ">", array.subList(0, n/2), "+", array.subList(n/2, n));		//　表示
		
		List array0 = mergesort(array.subList(0, n/2));		// 前半分をソート	
		List array1 = mergesort(array.subList(n/2, n));		// 後半分をソート
		
		List merged =merge(array0, array1);					// マージ
		
		Main.print(array0, "+", array1, ">", merged);		// 表示
		
		return merged;
	}

	
	// ２本のソート済みリストをマージして１本のソート済みリストにする
	// 先頭から走査していって小さい方をとっていく
	public static synchronized List merge(List array0, List array1){
		//
		// ここに正しいコードを書く
		//

		
		//
		// 以下はダミー　ただ交互につなげるだけ
		//
		int i=0;
	    int j=0;
	    List result = new ArrayList();
	    while(true){
	    	if (i<array0.size())
	    		add(result, get(array0, i++));
	    	if (j<array1.size())
	    		add(result, get(array1, j++));
	    	if ( i == array0.size() && j == array1.size())
		    	return result;
	    }
	}
	
	
	
	//以下は可読性向上のため (正しいコード)
	static int get(List list, int index){
		return ((Integer)list.get(index)).intValue();
	}
	static void add(List list, int val){
		list.add(new Integer(val));
	}
}


/*
正解例
public class MergeSort {

	public static List mergesort(List array){
		int n = array.size();
		if (n <= 1)											// 再帰呼び出しの末端
			return array;
		
		Main.print(array, ">", array.subList(0, n/2), "+", array.subList(n/2, n));		//　表示
		
		List array0 = mergesort(array.subList(0, n/2));		// 前半分をソート	
		List array1 = mergesort(array.subList(n/2, n));		// 後半分をソート
		
		List merged =merge(array0, array1);					// マージ
		
		Main.print(array0, "+", array1, ">", merged);		// 表示
		
		return merged;
	}

	// ２本のソート済みリストをマージして１本のソート済みリストにする
	// 先頭から走査していって小さい方をとっていく
	public static synchronized List merge(List array0, List array1){
	    int i=0;
	    int j=0;
	    List result = new ArrayList();
	    while(true){
	    	if (i<array0.size() && (j>=array1.size() || get(array0,i) < get(array1,j))){ 
	    		add(result, get(array0,i)); 	
	    		i=i+1; 
	    	}
	    	if (j<array1.size() && (i>=array0.size() || get(array0,i) > get(array1,j))){ 
	    		add(result, get(array1,j)); 	
	    		j=j+1;
	    	}
	    	if ( i == array0.size() && j == array1.size())
		    	return result;
	    }
	}
	
	//可読性向上のため
	static int get(List list, int index){
		return ((Integer)list.get(index)).intValue();
	}
	static void add(List list, int val){
		list.add(new Integer(val));
	}
}

*/