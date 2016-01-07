
public class Heap {
	Edge[] data = new Edge[1000];
	int last = -1;


	void insert(Edge object){
		// �Ō���ɗv�f��ǉ�
		last = last + 1;
		data[last] = object;		

		// ��ւƒH��Ȃ���t�]���������Ă���
		int i = last;	
		while( i > 0 ){
			if (data [(i-1)/2].cost > data [i].cost ){ // �e�̕����傫��
				swap(data, (i-1)/2, i);	//����ւ�
				i =(i-1)/2;			//�e�ֈړ�
			}
			else
				return;			// �I��
		}
	}
	Edge deletemin(){
		//���̗v�f�����o��
		Edge object = data[0];	
		// �Ō�������Ɏ����Ă���
		data[0] = data[last];
		last = last - 1;	

		//���ւƒH��Ȃ���t�]���������Ă��B
		int i = 0;
		while(i < last/2){
			if (data[i].cost > data[i*2+1].cost){		// �e > ���̎q
				if (data[i*2+2].cost < data[i*2+1].cost){ // �E�̎q < ���̎q
					swap(data, i, i*2+2);	//�E�̎q�ƌ���
					i = i*2+2; 			// �E�̎q�ֈړ�
				}
				else {				// ���̎q < �E�̎q
					swap(data, i, i*2+1);	//���̎q�ƌ���
					i = i*2+1;		//���̎q�ֈړ�
				}
			}
			else if (data[i].cost > data[i*2+2].cost){ 	//�e>�E�̎q
				swap(data, i, i*2+2);	//�E�̎q�ƌ���
				i = i*2+2;			//�E�̎q�ֈړ�
			}
			else
				return object;
		}
		return object;
	}

	// A��i�Ԗڂ�j�Ԗڂ̗v�f�����ւ���
	static void swap(Edge[] A, int i, int j){
		Edge tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
