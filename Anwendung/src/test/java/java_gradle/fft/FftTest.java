package java_gradle.fft;

import org.junit.Test;
import static org.junit.Assert.*;

public class FftTest {
   
    private static final int n = 4;
    private static final Complex[] x = new Complex[n];
    static {
        for (int i = 0; i < n; i++) {
            x[i] = new Complex(i, 0);
        }
    }

    @Test
    public void testFFT () { 
                
        // FFT of original data
        Complex[] y = FFT.fft(x);
 //       FFT.show(y, "y = FFT.fft(x)");
        assertEquals   ("FFT nicht richtig",  "[-2.0 + 2.0i]", y[1].toString());
        assertEquals   ("FFT nicht richtig",  "-1.9999999999999998 - 2.0i", y[3].toString());
        
        // take inverse FFT
        Complex[] z = FFT.ifft(y);
        FFT.show(z, "z = FFT.ifft(y)");
        assertEquals   ("inverse FFT nicht richtig", "[1.0 + 5.721188726109833E-18i]", z[1].toString());
        assertEquals   ("inverse FFT nicht richtig", "3.0 - 5.721188726109833E-18i", z[3].toString());
    }

    @Test
    public void testDFT () {
        // DFT of original data
        Complex[] y2 = FFT.dft(x);
//        FFT.show(y2, "y2 = FFT.dft(x)");
        assertEquals   ("DFT nicht richtig",  "6.0", y2[0].toString());
        assertEquals   ("DFT nicht richtig", "-2.0 - 4.898587196589413E-16i", y2[2].toString());
    }        
    
    @Test
    public void testCircularConvolution () {
        // circular convolution of x with itself
        Complex[] c = FFT.cconvolve(x, x);
//        FFT.show(c, "c = FFT.cconvolve(x, x)");
        assertEquals   ("circular convolution von "+x[1].toString(),  "12.0 - 2.2884754904439333E-17i", c[1].toString());
        assertEquals   ("circular convolution nicht richtig", "[4.0 + 2.2884754904439333E-17i]",  c[3].toString());
    }

    @Test
    public void testLinearConvolution () {
        // linear convolution of x with itself
        Complex[] d = FFT.convolve(x, x);
//        FFT.show(d, "d = FFT.convolve(x, x)");
        assertEquals   ("linear convolution nicht richtig", "8.881784197001252E-16 - 8.99620797152345E-16i", d[1].toString());
        assertEquals   ("linear convolution nicht richtig",  "[4.0 + 6.775761922273136E-16i]", d[3].toString());
        assertEquals   ("linear convolution nicht richtig",  "[12.0 + 8.767360422479055E-16i]", d[5].toString());
        assertEquals   ("linear convolution nicht richtig", "2.220446049250313E-16 - 6.546914373228742E-16i",  d[7].toString());
    }       
}
