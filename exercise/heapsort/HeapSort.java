public class HeapSort {

	//
	//	ヒープソート　　まずすべての要素をヒープにいれ、そのあとヒープから最小値を取り出していく。
	//
	static void heapsort(int[] A){
		int n = A.length;
		
		//
		// 以下はダミーのコード　ひたすらpushdownを呼ぶ
		//
		for(int i=0; i<n; i++){
			Main.print(A, i, n-1);	// 表示
			pushdown(A, i, n-1);
		}
	}

	//
	// 逆転がなくなるまで降ろしていく 
	//
	static void pushdown(int[] A, int first, int last){
		
		//
		// 以下はダミーのコード　何も考えずに左の子をたどりながら swap していく
		//
		int i = first;
		while (i <= (last-1)/2.0){	//2.0に注意!　(last-1)/2 だと、last=0のとき0になってしまう 
			int j = 2*i+1;	// 左の子へ
			swap(A, i, j);	// 親iと子jの内容を交換
			i = j;			// さらに子孫を調べる
		}
		
	}

	
	// Aのi番目とj番目の要素を入れ替える (正しいコード)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	
}
/*

正解例

public class HeapSort {

	static void heapsort(int[] A){
		// 配列Ａをヒープ(配列表現された半順序木)とみなす
		// 半順序性を確立し、Ａを完全なヒープに変換する
		int n = A.length;
		Main.print("put elements in a heap");
		for (int i=(n-1)/2; i>=0; i--){
			Main.print(A, i, n-1);	// 表示
			pushdown(A, i, n-1);	// i..n-1の間の半順序性を確保する
		}
			
		// ヒープから最小値を順次取出し、Ａの後ろへ並べていく
		Main.print("get elements from the heap");
		for (int i=n-1; i>=1; i--){
			Main.print(A, 0, i-1);	// 表示
			swap(A, 0, i);			// ヒープの先頭から最小値を取り除く
			pushdown(A, 0, i-1);	// 半順序性を回復する
		}
		
		// 最終的に大きい順に並んだ配列Ａが得られる。
	}

	// 逆転がなくなるまで降ろしていく
	static void pushdown(int[] A, int first, int last){
		int i = first;
		while (i <= (last-1)/2.0){											//2.0に注意!　(last-1)/2 だと、last=0のとき0になってしまう 
			int j = (2*i+2 > last || A[2*i+1]<A[2*i+2]) ? 2*i+1 : 2*i+2;	//iの子のうち値が小さい方の番号(2i+1または2i+2)
			if ( A[j] < A[i] ){
				swap(A, i, j);	// 親iと子jの内容を交換
				i = j;		// さらに子孫を調べる
			}
			else 
				return;		// 逆転はないので終了
		}
	}

	// Aのi番目とj番目の要素を入れ替える
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	
}


*/