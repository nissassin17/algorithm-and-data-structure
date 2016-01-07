import java.util.*;

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	// ヒープへの要素の追加
	void insert(int object){
		
		// 最後尾に要素を追加 (正しいコード)
		last = last + 1;
		data[last] = object;		
		for(int i = last; i > 0 && data[i] < data[(i - 1) / 2]; i = (i - 1)/2){
			Main.print(this, (i - 1) / 2, i);
			System.out.println(data[i]);
			swap(data, i, (i - 1) / 2);
		}

		
	}
	
	
	// 最小値の取り出し
	int deletemin(){
		
		//根の要素を取り出す (正しいコード)
		int object = data[0];
		
		// 最後尾を根に持ってくる  (正しいコード)
		data[0] = data[last];
		last = last - 1;	

		
		//下へと辿りながら逆転を解消してく。
		//
		//	ここにコードを書く
		//

		for(int i = 0; i * 2 < last; ){
			int t = i * 2 + 1;
			if (t < last && data[t + 1] < data[t])
				t = t + 1;
			if (data[t] < data[i]){
				Main.print(this, i, t);
				swap(data, i, t);
				i = t;
			}
			else break;
		}
		
		return object;
	} 
	
	// Aのi番目とj番目の要素を入れ替える(正しいコード)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
/*

正解例

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	// ヒープへの要素の追加
	void insert(int object){
		
		// 最後尾に要素を追加 (正しいコード)
		last = last + 1;
		data[last] = object;		

		
		// 上へと辿りながら逆転を解消していく
		//
		//	ここにコードを書く
		//

		
		// 以下、ダミーのコード　親とひとつだけ入れ替える
		if (last >= 1){
			Main.print(this, (last-1)/2, last);	//　表示
			swap(data, (last-1)/2, last);	//入れ替え
		}
		
	}
	
	
	// 最小値の取り出し
	int deletemin(){
		
		//根の要素を取り出す (正しいコード)
		int object = data[0];
		
		// 最後尾を根に持ってくる  (正しいコード)
		data[0] = data[last];
		last = last - 1;	

		
		//下へと辿りながら逆転を解消してく。
		//
		//	ここにコードを書く
		//

		
		// 以下、ダミーのコード　子とひとつだけ入れ替える
		int i = 0;
		Main.print(this, i, i*2+2);	//　表示
		swap(data, i, i*2+2);	//右の子と交換

		return object;
	} 
	
	// Aのi番目とj番目の要素を入れ替える(正しいコード)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
*/
