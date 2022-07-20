package java_gradle.algorithms;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortTest {
    
    private final int[] original    =  { 4, 0, 7, 6, 88, -3, 44, -1111 };
    private final int[] sorted      =  {-1111, -3, 0, 4, 6, 7, 44, 88};

    @Test
    public void testQuickSort() {
		
        assertArrayEquals   (sorted, QuickSort.sort(original));
	}

    @Test
    public void testQuickSortStream() {
		
        assertArrayEquals   (sorted, QuickSortStream.sort(original));
	}

    @Test
    public void testSelectionSort() {
		
        assertArrayEquals   (sorted, SelectionSort.sort(original));
	}

    @Test
    public void testSelectionSortFunctional() {
		
        assertArrayEquals   (sorted, SelectionSortFunctional.sort(original));
	}
}
