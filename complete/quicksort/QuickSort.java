public class QuickSort {

	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}



	
	// 注意 以下のコードでは 同一の要素が複数あると正常に動作しない
	static void sort(int[] array, int i, int j) {
		if (i == j) // 再帰呼び出しの末端
			return;

		Main.print(array, i, j); // 途中経過表示

		int pivot = find_pivot(array, i, j); // ピボットの計算
		int k = partition(array, i, j, pivot); // ピボットより小と大に分ける

		Main.print(array, i, k, j); // 途中経過表示

		sort(array, i, k - 1); // 小さい方をソート
		sort(array, k, j); // 大きい方をソート

		Main.print(array, i, j); // 途中経過表示
	}

	// ピボットの計算  l～rの間で最初に出てきた２つの異なる値のうち大きい方を返す
	static int find_pivot(int[] array, int l, int r) {
		for (int i = l + 1; i <= r; i++) {
			if (array[i] > array[l])
				return array[i];
			else if (array[i] < array[l])
				return array[l];
		}
		return array[l]; // ここまできた場合は全部同じだった。この場合の処理は別途必要。
	}

	// ピボットより小と大に分ける
	// 左右から攻めていって逆転があったらひっくり返していく。
	static int partition(int[] A, int l, int r, int pivot) {
		while (true) {
			while (A[l] < pivot)
				l = l + 1;
			while (A[r] >= pivot)
				r = r - 1;
			if (l > r)
				return l;
			swap(A, l, r); // Aのl番目とr番目の要素を入れ替える
		}
	}

	// Aのi番目とj番目の要素を入れ替える
	static void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

}
