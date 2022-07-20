package java_gradle.algorithms;

public class SelectionSort {

	public static int[] sort (int[] array) {
		
		int len			= array.length;
		for (int i=0;i<len;++i) {
			int min 		= i;
			for (int j=i;j<len;++j) {
				min				= (array[j]<array[min]) ? j : min;
			}
			exchange 	( array, i, min);
		}
		return 			array;
	}
	
	private static void exchange(int[] array, int offset, int min) {
		int x			= array[offset];
		array[offset]	= array[min];
		array[min]		= x;
	}
}
