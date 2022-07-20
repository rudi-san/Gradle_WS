package java_gradle.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KaratsubaTest {
    
    @Test
    public void testMultiply () {
        
        assertEquals("125", Karatsuba.multiply("5","25"));;
        assertEquals("8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184", 
        Karatsuba.multiply	( "3141592653589793238462643383279502884197169399375105820974944592"
                            , "2718281828459045235360287471352662497757247093699959574966967627"));
    }
}
