package java_gradle.algorithms;

public class SelectionSortFunctional {

    public static int[] sort (int[] array) {
		
		return		sortFunc (array, 0, array.length);
	}
	
	private static int[] sortFunc (int[] array, int offset, int len) {
		
		if (len==1) 			return	array;
		else {
			 exchange 	( array
					 	, offset
					 	, getMinIndex( array, offset, len, offset));
			 
			 return 	sortFunc( array
					 			, offset+1
					 			, len-1);
		}
	}


	private static int getMinIndex(int[] array, int offset, int len, int min) {
		
		if  (len==0)				return min;
		else						return getMinIndex(array, offset+1, len-1, (array[offset]<array[min]) ? offset : min);
		
	}

    private static void exchange(int[] array, int offset, int min) {
		int x			= array[offset];
		array[offset]	= array[min];
		array[min]		= x;
	}

}
