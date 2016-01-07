public class BubbleSort {
	
	// バブルソートを行う
	public static void sort(int[] array){
		int n = array.length;
		
		// 以下ダミーのコード　単純に左から右へ移動しながらswap していく
		for(int i=0; i<n-1; i++){				
			Main.print(array, i, i+1);	//　表示
			swap(array, i, i+1); 		//arrayのj番目とj-1番目の要素を交換
		}
		for(int i = 0; i < n - 1; i++){
			Main.print(array, i, n - 1);
			for(int j = i + 1; j < n; j++)
				if (array[i] > array[j]){
					Main.print(array, i, j, n - 1);
					swap(array, i, j);
				}
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
	解答例
public class BubbleSort {
	
	public static void sort(int[] array){
		int n = array.length;
		for(int i=0; i<=n-2; i++){				// 上から順に一番小さい数をつめていく。
			Main.print(array, i, n-1);			//　表示
			for(int j=n-1; j>=i+1; j--){		// 下から上へ走査していく
				Main.print(array, i, j, n-1);	//　表示
				if ( array[j] < array[j-1])
					swap(array, j, j-1); 		//arrayのj番目とj-1番目の要素を交換
			}
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
