import java.util.*;

public class Heap {
	Vertex[] data = new Vertex[1000];
	int last = -1;

	boolean is_empty(){
		return (last == -1);
	}
	void insert(Vertex object){
		// 最後尾に要素を追加
		last = last + 1;
		data[last] = object;		

		// 上へと辿りながら逆転を解消していく
		int i = last;	
		while( i > 0 ){
			if (data [(i-1)/2].cost > data [i].cost ){ // 親の方が大きい
				swap(data, (i-1)/2, i);	//入れ替え
				i =(i-1)/2;			//親へ移動
			}
			else
				return;			// 終了
		}
	}
	Vertex deletemin(){
		//根の要素を取り出す
		Vertex object = data[0];	
		// 最後尾を根に持ってくる
		data[0] = data[last];
		last = last - 1;	

		//下へと辿りながら逆転を解消してく。
		int i = 0;
		while(i < last/2){
			if (data[i].cost > data[i*2+1].cost){		// 親 > 左の子
				if (data[i*2+2].cost < data[i*2+1].cost){ // 右の子 < 左の子
					swap(data, i, i*2+2);	//右の子と交換
					i = i*2+2; 			// 右の子へ移動
				}
				else {				// 左の子 < 右の子
					swap(data, i, i*2+1);	//左の子と交換
					i = i*2+1;		//左の子へ移動
				}
			}
			else if (data[i].cost > data[i*2+2].cost){ 	//親>右の子
				swap(data, i, i*2+2);	//右の子と交換
				i = i*2+2;			//右の子へ移動
			}
			else
				return object;
		}
		return object;
	}
	void update(Vertex v){
		for(int i=0; i<=last; i++){
			if (data[i] == v){
				while(i>0){
					int parent = (i-1)/2;
					if (data[parent].cost > data[i].cost){
						swap(data, parent, i);
						i = parent;
					}
					else
						return;
				}
			}
		}
	}
	// Aのi番目とj番目の要素を入れ替える
	static void swap(Vertex[] A, int i, int j){
		Vertex tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
