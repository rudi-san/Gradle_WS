package java_gradle.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FunctionTest {
    
    public final int[][] values		= { { 1,1 }, { 22,2 }, { 11, 3 } };
    
    @Test
    public void testAdd() {
        try {
            assertEquals    (2, FunctionExample.rechne(values[0], FunctionExample.getFunction("add")));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testMultiply() {
        try {
            assertEquals    (44, FunctionExample.rechne(values[1], FunctionExample.getFunction("multiply")));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPotenz() {
        try {
            assertEquals    (1331, FunctionExample.rechne(values[2], FunctionExample.getFunction("potenz")));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUnknownFunction() {
        try {
            assertEquals    (0, FunctionExample.rechne(values[0], FunctionExample.getFunction("rechneroemischezahlen")));
            fail("keine Exception geworfen");
        } catch (Exception e) {
        }

	}

}
