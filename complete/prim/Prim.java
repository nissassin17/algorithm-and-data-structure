import java.util.*;

public class Prim {
	static List<int[]> compute_minimum_spanning_tree(UndirectedGraph G){
		
		// ����
		int n = G.n;					//�@���_��	
		int[][] d = G.d;				// �����s��
		
		int s = 0;						//	s �� V�̔C�ӂ̐ߓ_	
		
		int[] C = new int[n];			// C[v] �� d[s, v];	// �\�z���̖؂Ɩ������ߓ_v�̊Ԃ̃R�X�g
		for(int i=0; i<n; i++)
			C[i] = d[s][i];
				
		int[] N = new int[n];			// N[v] �� s;	// v�ɍł��߂��\�z���̖؂̒��̐ߓ_
		for(int i=0; i<n; i++)
			N[i] = s;
		
		IntSet U = new IntSet();		//U �� V-s		//�������ߓ_�̏W��
		for(int i=1; i<n; i++)
			U.add(i);
			
		List<int[]> T = new ArrayList<int[]>();	//	T �� s;		//�R�X�g�ŏ��̋ɑ��
		
		while(U.size()>0){
			int w = -1;						// w �� �t�̂���C[w] ���ŏ��ƂȂ����
			int min = Integer.MAX_VALUE;
			for(int i=0; i<U.size(); i++){
				int v = U.get(i);
				if (C[v] < min){
					min = C[v];
					w = v;
				}
			}
			
			U.remove(w);					//	U����w����菜��
			
			int[] edge = { w, N[w] };
			T.add( edge );					// T �ɕ�{w, N[w]}��������
			
			Main.print(G, T, C, w);			// �\��
			
			for (int i=0; i<U.size(); i++ ) {
			   int v = U.get(i);
			   if ( d[w][v] < C[v]){
				   C[v] = d[w][v]; 
				   N[v] = w; 				//�X�V
			   }
			}
		}
		return T;
	}
}
