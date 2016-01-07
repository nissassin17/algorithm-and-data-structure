import java.util.*;
public class MergeSort {

	public static List mergesort(List array){
		int n = array.size();
		if (n <= 1)											// �ċA�Ăяo���̖��[
			return array;
		
		Main.print(array, ">", array.subList(0, n/2), "+", array.subList(n/2, n));		//�@�\��
		
		List array0 = mergesort(array.subList(0, n/2));		// �O�������\�[�g	
		List array1 = mergesort(array.subList(n/2, n));		// �㔼�����\�[�g
		
		List merged =merge(array0, array1);					// �}�[�W
		
		Main.print(array0, "+", array1, ">", merged);		// �\��
		
		return merged;
	}

	// �Q�{�̃\�[�g�ς݃��X�g���}�[�W���ĂP�{�̃\�[�g�ς݃��X�g�ɂ���
	// �擪���瑖�����Ă����ď����������Ƃ��Ă���
	public static synchronized List merge(List array0, List array1){
	    int i=0;
	    int j=0;
	    List result = new ArrayList();
	    while(true){
	    	if (i<array0.size() && (j>=array1.size() || get(array0,i) < get(array1,j))){ 
	    		add(result, get(array0,i)); 	
	    		i=i+1; 
	    	}
	    	if (j<array1.size() && (i>=array0.size() || get(array0,i) > get(array1,j))){ 
	    		add(result, get(array1,j)); 	
	    		j=j+1;
	    	}
	    	if ( i == array0.size() && j == array1.size())
		    	return result;
	    }
	}
	
	//�ǐ�����̂���
	static int get(List list, int index){
		return ((Integer)list.get(index)).intValue();
	}
	static void add(List list, int val){
		list.add(new Integer(val));
	}
}
