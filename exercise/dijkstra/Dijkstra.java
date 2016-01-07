public class Dijkstra {

	// �_�C�N�X�g���̃A���S���Y���ɂ���čŒZ�������v�Z����  
	// ���̓f�[�^ graph.edges�@�̊e�v�f edge �̃R�X�g edge.cost
	// �o�̓f�[�^ start_vertex����graph.vertices �̊e�v�f v �܂ł̍ŒZ���� v.cost
	
	static void dijkstra(DirectedGraph graph, Vertex start_vertex){

	    //�@�����@(�������R�[�h)
		for(Vertex v : graph.vertices)	// �������B���ڈړ��̃R�X�g��C�ɃZ�b�g����B
	        v.cost = Integer.MAX_VALUE;
	    for(Edge edge : start_vertex.edges)
	    	edge.end.cost = edge.cost;
	    start_vertex.cost = 0;
	
	    Heap heap = new Heap();
	    for(Vertex v : graph.vertices)
	    	heap.insert(v);				// �q�[�v S �ɂu�̂��ׂĂ̗v�fv��������B������C[v]����Ƃ���B

	    

	    // ���C���̌v�Z����
	    //�@heap���珇�Ɏ��o���A cost ���X�V���Ă���
	    
	    // �ȉ��A�_�~�[�̃R�[�h �����P�� heap ������o���Ă�������
	    while( !heap.is_empty() ){						
	       Vertex w = heap.deletemin();	// w �� S����C[w]���ŏ��̒��_w�����o��
	       Main.print(graph, w);		// �\��
	    }
	}
}

/*
�𓚗�
public class Dijkstra {

	static void dijkstra(DirectedGraph graph, Vertex start_vertex){

	    for(Vertex v : graph.vertices)	// �������B���ڈړ��̃R�X�g��C�ɃZ�b�g����B
	        v.cost = Integer.MAX_VALUE;
	    for(Edge edge : start_vertex.edges)
	    	edge.end.cost = edge.cost;
	    start_vertex.cost = 0;
	    
	    Heap heap = new Heap();
	    for(Vertex v : graph.vertices)
	    	heap.insert(v);				// �q�[�v S �ɂu�̂��ׂĂ̗v�fv��������B������C[v]����Ƃ���B
	    	
	    while( !heap.is_empty() ){						
	       Vertex w = heap.deletemin();	// w �� S����C[w]���ŏ��̒��_w�����o��
	       Main.print(graph, w);		// �\��
	       for(Edge edge : w.edges){	// v �� w����o�Ă���ӂ̍s����)
	    	   Vertex v = edge.end;
	    	   if (w.cost == Integer.MAX_VALUE)
	    		   continue;						// ����: MAX�ɑ����Ă��܂��ƃI�[�o�[�t���[����c
	    	   if ( v.cost > w.cost + edge.cost ){
	    		   v.cost = w.cost + edge.cost;	 	// �R�X�g�̍X�V
	    		   heap.update(v);               	// �q�[�v�ɂ�����v�̈ʒu��C[v]�ɏ]���ČJ��グ�� �i�t�]���Ȃ��Ȃ�܂ŏ�ɏグ�Ă����B�j
	    	   }
	       }
	    }
	}

}

*/