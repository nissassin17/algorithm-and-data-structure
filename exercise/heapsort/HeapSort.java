public class HeapSort {

	//
	//	�q�[�v�\�[�g�@�@�܂����ׂĂ̗v�f���q�[�v�ɂ���A���̂��ƃq�[�v����ŏ��l�����o���Ă����B
	//
	static void heapsort(int[] A){
		int n = A.length;
		
		//
		// �ȉ��̓_�~�[�̃R�[�h�@�Ђ�����pushdown���Ă�
		//
		for(int i=0; i<n; i++){
			Main.print(A, i, n-1);	// �\��
			pushdown(A, i, n-1);
		}
	}

	//
	// �t�]���Ȃ��Ȃ�܂ō~�낵�Ă��� 
	//
	static void pushdown(int[] A, int first, int last){
		
		//
		// �ȉ��̓_�~�[�̃R�[�h�@�����l�����ɍ��̎q�����ǂ�Ȃ��� swap ���Ă���
		//
		int i = first;
		while (i <= (last-1)/2.0){	//2.0�ɒ���!�@(last-1)/2 ���ƁAlast=0�̂Ƃ�0�ɂȂ��Ă��܂� 
			int j = 2*i+1;	// ���̎q��
			swap(A, i, j);	// �ei�Ǝqj�̓��e������
			i = j;			// ����Ɏq���𒲂ׂ�
		}
		
	}

	
	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ��� (�������R�[�h)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	
}
/*

�����

public class HeapSort {

	static void heapsort(int[] A){
		// �z��`���q�[�v(�z��\�����ꂽ��������)�Ƃ݂Ȃ�
		// �����������m�����A�`�����S�ȃq�[�v�ɕϊ�����
		int n = A.length;
		Main.print("put elements in a heap");
		for (int i=(n-1)/2; i>=0; i--){
			Main.print(A, i, n-1);	// �\��
			pushdown(A, i, n-1);	// i..n-1�̊Ԃ̔����������m�ۂ���
		}
			
		// �q�[�v����ŏ��l��������o���A�`�̌��֕��ׂĂ���
		Main.print("get elements from the heap");
		for (int i=n-1; i>=1; i--){
			Main.print(A, 0, i-1);	// �\��
			swap(A, 0, i);			// �q�[�v�̐擪����ŏ��l����菜��
			pushdown(A, 0, i-1);	// �����������񕜂���
		}
		
		// �ŏI�I�ɑ傫�����ɕ��񂾔z��`��������B
	}

	// �t�]���Ȃ��Ȃ�܂ō~�낵�Ă���
	static void pushdown(int[] A, int first, int last){
		int i = first;
		while (i <= (last-1)/2.0){											//2.0�ɒ���!�@(last-1)/2 ���ƁAlast=0�̂Ƃ�0�ɂȂ��Ă��܂� 
			int j = (2*i+2 > last || A[2*i+1]<A[2*i+2]) ? 2*i+1 : 2*i+2;	//i�̎q�̂����l�����������̔ԍ�(2i+1�܂���2i+2)
			if ( A[j] < A[i] ){
				swap(A, i, j);	// �ei�Ǝqj�̓��e������
				i = j;		// ����Ɏq���𒲂ׂ�
			}
			else 
				return;		// �t�]�͂Ȃ��̂ŏI��
		}
	}

	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	
}


*/