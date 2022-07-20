package java_gradle.algorithms;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickSortStream {

	private static int[] array;
	private static Random random	= new Random();
	
	public static void main(String[] args) {
		
		sortPart(array).forEach(System.out::println);

	}

	public static int[] sort (int[] original) {
		array		= original;
		return 		sortPart(array).toArray();
	}

	private static IntStream sortPart(int[] array) {
	
		int len				= array.length;
		if (len<2)			return Arrays.stream(array);
		
		int pivot			= array[random.nextInt(len)];
		IntStream vor		= sortPart(Arrays.stream(array).filter(x -> x<pivot).toArray());
		IntStream nach		= sortPart(Arrays.stream(array).filter(x -> x>pivot).toArray());
		
		return				IntStream.concat(IntStream.concat(vor, IntStream.of(pivot)), nach);
	}
}
