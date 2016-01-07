public class QuickSort {

	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}



	
	// ���� �ȉ��̃R�[�h�ł� ����̗v�f����������Ɛ���ɓ��삵�Ȃ�
	static void sort(int[] array, int i, int j) {
		if (i == j) // �ċA�Ăяo���̖��[
			return;

		Main.print(array, i, j); // �r���o�ߕ\��

		int pivot = find_pivot(array, i, j); // �s�{�b�g�̌v�Z
		int k = partition(array, i, j, pivot); // �s�{�b�g��菬�Ƒ�ɕ�����

		Main.print(array, i, k, j); // �r���o�ߕ\��

		sort(array, i, k - 1); // �����������\�[�g
		sort(array, k, j); // �傫�������\�[�g

		Main.print(array, i, j); // �r���o�ߕ\��
	}

	// �s�{�b�g�̌v�Z  l�`r�̊Ԃōŏ��ɏo�Ă����Q�̈قȂ�l�̂����傫������Ԃ�
	static int find_pivot(int[] array, int l, int r) {
		for (int i = l + 1; i <= r; i++) {
			if (array[i] > array[l])
				return array[i];
			else if (array[i] < array[l])
				return array[l];
		}
		return array[l]; // �����܂ł����ꍇ�͑S�������������B���̏ꍇ�̏����͕ʓr�K�v�B
	}

	// �s�{�b�g��菬�Ƒ�ɕ�����
	// ���E����U�߂Ă����ċt�]����������Ђ�����Ԃ��Ă����B
	static int partition(int[] A, int l, int r, int pivot) {
		while (true) {
			while (A[l] < pivot)
				l = l + 1;
			while (A[r] >= pivot)
				r = r - 1;
			if (l > r)
				return l;
			swap(A, l, r); // A��l�Ԗڂ�r�Ԗڂ̗v�f�����ւ���
		}
	}

	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���
	static void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

}
