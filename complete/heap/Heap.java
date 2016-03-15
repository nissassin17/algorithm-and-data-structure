import java.util.*;

public class Heap {
	int[] data = new int[1000];
	int last = -1;

	void insert(int object){
		// �Ō���ɗv�f��ǉ�
		last = last + 1;
		data[last] = object;		

		// ��ւƒH��Ȃ���t�]���������Ă���
		int i = last;	
		while( i > 0 ){
			if (data [(i-1)/2] > data [i] ){ // �e�̕����傫��
				Main.print(this, (i-1)/2, i);	//�@�\��
				swap(data, (i-1)/2, i);	//����ւ�
				i =(i-1)/2;			//�e�ֈړ�
			}
			else
				return;			// �I��
		}
	}
	int deletemin(){
		//���̗v�f�����o��
		int object = data[0];	
		// �Ō�������Ɏ����Ă���
		data[0] = data[last];
		last = last - 1;	

		//���ւƒH��Ȃ���t�]���������Ă��B
		int i = 0;
		while(i < last/2){
			if (data[i] > data[i*2+1]){		// �e > ���̎q
				if (data[i*2+2] < data[i*2+1]){ // �E�̎q < ���̎q
					Main.print(this, i, i*2+2);	//�@�\��
					swap(data, i, i*2+2);	//�E�̎q�ƌ���
					i = i*2+2; 			// �E�̎q�ֈړ�
				}
				else {				// ���̎q < �E�̎q
					Main.print(this, i, i*2+1);	//�@�\��
					swap(data, i, i*2+1);	//���̎q�ƌ���
					i = i*2+1;		//���̎q�ֈړ�
				}
			}
			else if (data[i] > data[i*2+2]){ 	//�e>�E�̎q
				Main.print(this, i, i*2+2);	//�@�\��
				swap(data, i, i*2+2);	//�E�̎q�ƌ���
				i = i*2+2;			//�E�̎q�ֈړ�
			}
			else
				return object;
		}
		return object;
	}
	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���
	static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
