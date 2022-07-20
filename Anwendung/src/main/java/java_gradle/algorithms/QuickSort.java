package java_gradle.algorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	private static int[] array;
	private static Random random	= new Random();
	
	public static void main(String[] args) {
		
		sortPart					(0, array.length-1);
		System.out.println		(Arrays.toString(array));

	}

	public static int[] sort (int[] original) {
		array			= original;
		sortPart		(0, array.length-1);
		return			array;
	}

	private static void sortPart(int from, int to) {
	
		if (to-from>0) {	
			swapRandom		(from, to);
			int pivPos		= partitionFunc(from, to, from+1, from+1);
			sortPart			(from, pivPos-1);
			sortPart			(pivPos+1, to);
		}
	}
	
//	private static int partition (int from, int to) {
//		
//		int j = from+1; 
//		for (int i=j;i<=to;++i) {
//			if (array[i]<array[from]) { 
//				swap 		(i, j);
//				++j;
//			}
//		}
//		swap		(from, j-1);
//		return 		j-1;
//	}
//
	private static int partitionFunc (int from, int to, int i, int j) {
		
		if (i>to) {
			swap		(from, j-1);
			return		j-1;
		}
		else
			return		partitionFunc(from, to, i+1, test(from, i, j));
	}
	
	private static int test (int from, int i, int j) {
		if (array[i]<array[from]) { 
			swap 		(i, j);
			return		j+1;
		}
		else
			return		j;
	}

	private static void swap(int i, int j) {
		int x		= array[i];
		array[i]	= array[j];
		array[j]	= x;
	}
	
	private static void swapRandom (int from, int to) {
		swap 		(from, from+random.nextInt(to-from)); 
	}

}
