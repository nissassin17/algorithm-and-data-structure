import java.util.*;

public class Kruskal {
	
	// �N���X�J���̃A���S���Y���ōŏ��؂����߂�
	// ���́@G.edges �̗v�fedge �̃R�X�g edge.cost
	// �o�́@�ŏ��؂ɑ����� edge�̏W��
	
	static List<Edge> compute_minimum_spanning_tree(UndirectedGraph G){
		
		// �����@(�������R�[�h)

		// �ŏ��؂̏���
		List<Edge> mst = new ArrayList<Edge>();
		
		//�D��x�t�҂��s��U��p�ӂ��A���ׂĂ̕ӂ�}������
		Heap U = new Heap();									
		for(Edge edge: G.edges)
			U.insert(edge);
		
		//�@�W���Q�̏���
		MergeFindSet mfset = new MergeFindSet(G.vertices);	// MergeFindSet��p�ӂ���
		
		
		// �ȉ��A�_�~�[�̃R�[�h�@�P�ɏ��Ɏ��o���ĉ����Ă���
		for(int i=0; i<G.edges.size(); i++){
			Edge edge = U.deletemin();				// U����R�X�g���ŏ��̕�{i,j}�����o��			
		    mst.add(edge);
			Main.print(G, mst, edge);				// �\��
		}
		
		return mst;
	}
	
}

/*
public class Kruskal {
	
	// �N���X�J���̃A���S���Y���ōŏ��؂����߂�
	// ���́@G.edges �̗v�fedge �̃R�X�g edge.cost
	// �o�́@�ŏ��؂ɑ����� edge�̏W��
	
	static List<Edge> compute_minimum_spanning_tree(UndirectedGraph G){
		
		List<Edge> mst = new ArrayList<Edge>();
		
		Heap U = new Heap();							//�D��x�t�҂��s��U��p�ӂ��A���ׂĂ̕ӂ�}������		
		for(Edge edge: G.edges)
			U.insert(edge);
		
		MergeFindSet mfset = new MergeFindSet(G.vertices);	// MergeFindSet��p�ӂ���
		
		while(mst.size() < G.vertices.size()-1){
			Edge edge = U.deletemin();				// U����R�X�g���ŏ��̕�{i,j}�����o��			
			Set Ti = mfset.find(edge.start);		// Ti �� i���܂ޕ�����					
			Set Tj = mfset.find(edge.end);			// Tj �� j���܂ޕ�����			
			if (Ti != Tj) {
			    mst.add(edge);
			    mfset.merge(Ti, Tj);				//Ti��Ti�ƕ�{i,j}�łȂ���������T������
			}
		    
			Main.print(G, mst, edge);				// �\��
		}
		return mst;
	}
	
}


*/