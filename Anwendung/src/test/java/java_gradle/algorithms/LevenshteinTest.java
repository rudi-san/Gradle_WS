package java_gradle.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LevenshteinTest {

    @Test
    public void testLevenshtein () {
        
		String[][] cString	= 
			{ 	{ "Maus", "Mouse" },
				{ "Maus", "XMaus" },
				{ "Plumpaquatsch", "Radieschen" },
				{ "Test", "Test" }
			};
		assertEquals(2, LevenshteinDistance.computeLevenshteinDistance(cString[0][0], cString[0][1]));
		assertEquals(1, LevenshteinDistance.computeLevenshteinDistance(cString[1][0], cString[1][1]));
		assertEquals(11, LevenshteinDistance.computeLevenshteinDistance(cString[2][0], cString[2][1]));
		assertEquals(0, LevenshteinDistance.computeLevenshteinDistance(cString[3][0], cString[3][1]));
	}
}
