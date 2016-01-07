import java.util.*;

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	void insert(int object){
		// 最後尾に要素を追加
		last = last + 1;
		data[last] = object;		

		// 上へと辿りながら逆転を解消していく
		int i = last;	
		while( i > 0 ){
			if (data [(i-1)/2] > data [i] ){ // 親の方が大きい
				Main.print(this, (i-1)/2, i);	//　表示
				swap(data, (i-1)/2, i);	//入れ替え
				i =(i-1)/2;			//親へ移動
			}
			else
				return;			// 終了
		}
	}
	int deletemin(){
		//根の要素を取り出す
		int object = data[0];	
		// 最後尾を根に持ってくる
		data[0] = data[last];
		last = last - 1;	

		//下へと辿りながら逆転を解消してく。
		int i = 0;
		while(i < last/2){
			if (data[i] > data[i*2+1]){		// 親 > 左の子
				if (data[i*2+2] < data[i*2+1]){ // 右の子 < 左の子
					Main.print(this, i, i*2+2);	//　表示
					swap(data, i, i*2+2);	//右の子と交換
					i = i*2+2; 			// 右の子へ移動
				}
				else {				// 左の子 < 右の子
					Main.print(this, i, i*2+1);	//　表示
					swap(data, i, i*2+1);	//左の子と交換
					i = i*2+1;		//左の子へ移動
				}
			}
			else if (data[i] > data[i*2+2]){ 	//親>右の子
				Main.print(this, i, i*2+2);	//　表示
				swap(data, i, i*2+2);	//右の子と交換
				i = i*2+2;			//右の子へ移動
			}
			else
				return object;
		}
		return object;
	}
	// Aのi番目とj番目の要素を入れ替える
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
