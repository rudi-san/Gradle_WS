package java_gradle.fft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplexTest {

    private static final Complex a = new Complex(5.0, 6.0);
    private static final Complex b = new Complex(-3.0, 4.0);

    @Test
    public void testComplexTeile () {
        assertEquals("Real-Teil von "+a.toString(), 5.0, a.re(), 0.00001);
        assertEquals("Imaginär-Teil von "+a.toString(), 6.0, a.im(), 0.00001);
    }
    
    @Test
    public void testComplexBasic () {
        assertEquals(b.toString()+" + "+a.toString(), "[2.0 + 10.0i]",b.plus(a).toString());
        assertEquals(a.toString()+" - "+b.toString(), "[8.0 + 2.0i]",a.minus(b).toString());
        assertEquals(a.toString()+" * "+b.toString(), "[-39.0 + 2.0i]",a.times(b).toString());
        assertEquals(b.toString()+" * "+a.toString(), "[-39.0 + 2.0i]",b.times(a).toString());
        assertEquals(a.toString()+" / "+b.toString(), "0.36 - 1.52i",a.divides(b).toString());
        assertEquals("("+a.toString()+" / "+b.toString()+") * "+b.toString(), "[5.0 + 6.0i]",a.divides(b).times(b).toString());
    }

    @Test
    public void testComplexAdvanced () {
        assertEquals("conj("+a.toString()+")", "5.0 - 6.0i",a.conjugate().toString());
        assertEquals("|"+a.toString()+"|", 7.810249675906654, a.abs(), 0.0001);
        assertEquals(a.toString()+".tan()", "[-6.685231390246571E-6 + 1.0000103108981198i]", a.tan().toString());  
    }
}
