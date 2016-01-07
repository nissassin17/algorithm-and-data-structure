import java.util.*;

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	// �q�[�v�ւ̗v�f�̒ǉ�
	void insert(int object){
		
		// �Ō���ɗv�f��ǉ� (�������R�[�h)
		last = last + 1;
		data[last] = object;		
		for(int i = last; i > 0 && data[i] < data[(i - 1) / 2]; i = (i - 1)/2){
			Main.print(this, (i - 1) / 2, i);
			System.out.println(data[i]);
			swap(data, i, (i - 1) / 2);
		}

		
	}
	
	
	// �ŏ��l�̎��o��
	int deletemin(){
		
		//���̗v�f�����o�� (�������R�[�h)
		int object = data[0];
		
		// �Ō�������Ɏ����Ă���  (�������R�[�h)
		data[0] = data[last];
		last = last - 1;	

		
		//���ւƒH��Ȃ���t�]���������Ă��B
		//
		//	�����ɃR�[�h������
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
	
	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���(�������R�[�h)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
/*

�����

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	// �q�[�v�ւ̗v�f�̒ǉ�
	void insert(int object){
		
		// �Ō���ɗv�f��ǉ� (�������R�[�h)
		last = last + 1;
		data[last] = object;		

		
		// ��ւƒH��Ȃ���t�]���������Ă���
		//
		//	�����ɃR�[�h������
		//

		
		// �ȉ��A�_�~�[�̃R�[�h�@�e�ƂЂƂ�������ւ���
		if (last >= 1){
			Main.print(this, (last-1)/2, last);	//�@�\��
			swap(data, (last-1)/2, last);	//����ւ�
		}
		
	}
	
	
	// �ŏ��l�̎��o��
	int deletemin(){
		
		//���̗v�f�����o�� (�������R�[�h)
		int object = data[0];
		
		// �Ō�������Ɏ����Ă���  (�������R�[�h)
		data[0] = data[last];
		last = last - 1;	

		
		//���ւƒH��Ȃ���t�]���������Ă��B
		//
		//	�����ɃR�[�h������
		//

		
		// �ȉ��A�_�~�[�̃R�[�h�@�q�ƂЂƂ�������ւ���
		int i = 0;
		Main.print(this, i, i*2+2);	//�@�\��
		swap(data, i, i*2+2);	//�E�̎q�ƌ���

		return object;
	} 
	
	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���(�������R�[�h)
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
*/
