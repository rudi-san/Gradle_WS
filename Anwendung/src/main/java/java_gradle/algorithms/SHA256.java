package java_gradle.algorithms;

import java.util.Arrays;

public class SHA256 {

	static private int[] k 			= 	{ 0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5
	            						, 0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174
	            						, 0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da
	            						, 0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967
	            						, 0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85
	            						, 0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070
	            						, 0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3
	            						, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2 };
		
	public static byte[] encode (String msg) {
		byte[] padded			= padding(msg);
		int[][] chunks			= makeChunks(padded);
		int[] hashvalue			= process(chunks);
		return		 			intArrayAsByte(hashvalue);
	}
	
	public static byte[] padding (String message) {
		byte[] msgBytes 		= message.getBytes() ;
		byte[] minByte			= { (byte)0b10000000 };
		byte[] startBytes		= add(msgBytes, minByte);
		int len					= startBytes.length;
		int rest				= 64 - (len % 64);
		int lenRest				= (rest>8) ? rest - 8 : rest + 56;
		byte[] nullBytes		= new byte[lenRest];
		byte[] lenBytes			= longAsByte((len-1)*8L);
		byte[] ret				= add(msgBytes, add(minByte, add(nullBytes, lenBytes)));
		return 					ret;
	}

	public static int[][] makeChunks (byte[] padded) {
		int len				= padded.length;
		int[][] newArray	= new int[len/64][16];
		for (int i=0;i<newArray.length;++i) {
			for (int j=0;j<16;++j) {
				int k	= j * 4;
				byte[] entry	= { padded[i*64+k], padded[i*64+k+1], padded[i*64+k+2], padded[i*64+k+3] };
				newArray[i][j]	= byteAsInt(entry);
			}
		}
		return 		newArray;
	}
		
	public static byte[] add (byte[] b1, byte[] b2) {
		int b1Len		= b1.length;
		int b2Len		= b2.length;
		int newLen		= b1Len + b2Len;
		byte[] newByte	= Arrays.copyOf(b1, newLen);
		for (int i=0;i<b2Len;++i) {
			newByte[b1Len+i] = b2[i];
		}
		return newByte;
	}

	
	public static byte[] longAsByte (long x) {
		byte[] intBytes		= new byte[8];
		for (int i=0;i<8;++i) {
			long links		= x << i*8;
			long rechts		= links >>> 56;
			intBytes[i]		= (byte)rechts;
		}
		return intBytes;
	}
	
	public static byte[] intAsByte (int x) {
		byte[] intBytes		= new byte[4];
		for (int i=0;i<4;++i) {
			int links		= x << i*8;
			int rechts		= links >>> 24;
			intBytes[i]		= (byte)rechts;
		}
		return intBytes;
	}
	
	public static byte[] intArrayAsByte (int[] x) {
		int len				= x.length;
		byte[] intBytes		= new byte[0];
		for (int i=0;i<len;++i) {
			intBytes			= add(intBytes, intAsByte(x[i]));
		}
		return intBytes;
	}
	
	public static int byteAsInt (byte[] array) {
		int ergebnis		= 0;
		int len				= array.length;
		for (int i=0;i<len;++i) {
			int unsigned	= Byte.toUnsignedInt(array[i]);
			ergebnis 		+= unsigned*power(256, len-i-1);
		}
		return ergebnis;
	}
	
	private static int[] process (int[][] plain) {
		int[] last			= 	{ 0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a
								, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19  };
		for (int[] zeile : plain) {
			int[] w				= new int[64];
			for (int i=0;  i<16; ++i) 
				w[i] 				= zeile[i];
            for (int i=16; i<64; ++i) {
                w[i] = (sigma1(w[i-2]) + w[i-7] + sigma0(w[i-15]) + w[i-16]) >>> 0;
            }
            int a 				= last[0];
            int b 				= last[1];
            int c 				= last[2];
            int d 				= last[3];
            int e 				= last[4];
            int f 				= last[5];
            int g 				= last[6];
            int h 				= last[7];
			for (int j=0;j<64;++j) {
				int t1 	= h + sum1(e) + ch(e, f, g) + k[j] + w[j];
				int t2 	=     sum0(a) + maj(a, b, c);
                h 		= g;
                g 		= f;
                f 		= e;
                e 		= d + t1;
                d 		= c;
                c 		= b;
                b 		= a;
                a 		= t1 + t2;
			}
			last[0]				+= a;
			last[1]				+= b;
			last[2]				+= c;
			last[3]				+= d;
			last[4]				+= e;
			last[5]				+= f;
			last[6]				+= g;
			last[7]				+= h;
		}
		return 					last;
	}
	
	public static long power (long basis, int exponent) {
		if (exponent==0) 		{ return	1; }
		else					{ return 	basis * power(basis, exponent-1);	}
	}
	
	private static int sum0		(int x) 				{ return rotr(2,  x) ^ rotr(13, x) ^ rotr(22, x); 	}
	private static int sum1		(int x) 				{ return rotr(6,  x) ^ rotr(11, x) ^ rotr(25, x); 	}
	private static int sigma0	(int x) 				{ return rotr(7,  x) ^ rotr(18, x) ^ (x>>>3);  		}
	private static int sigma1	(int x) 				{ return rotr(17, x) ^ rotr(19, x) ^ (x>>>10); 		}
	private static int ch		(int x, int y, int z)  	{ return (x & y) ^ (~x & z); 						} // 'choice'
	private static int maj		(int x, int y, int z) 	{ return (x & y) ^ (x & z) ^ (y & z); 				} // 'majority'
	public static int rotr		(int n, int x) 			{ return (x >>> n) | (x << (32-n));					} // 'rotate right'

	public static void printLineal (int len) {
		for (int i=0;i<len;i+=100)
			System.out.print		("- - - - + - - - - 1 - - - - + - - - - 2 - - - - + - - - - 3 - - - - + - - - - 4 - - - - + - - - - 5 - - - - + - - - - 6 - - - - + - - - - 7 - - - - + - - - - 8 - - - - + - - - - 9 - - - - + - - - - 0");
		System.out.println		();
		
	}
	
	public static void printHex (byte[] bytes) {
		StringBuffer buf		= new StringBuffer();
		for (byte b : bytes) {
			buf.append				(byte2Hex(b));
		}
		System.out.println		(buf.toString()+"<--");
	}

	public static void printHex (int[] werte) {
		StringBuffer buf		= new StringBuffer();
		for (int b : werte) {
			buf.append				(int2Hex(b));
		}
		System.out.println		(buf.toString()+"<--");
	}

	public static void printPlain (int[][] intArray) {
		for (int[] zeile : intArray) {
			printHex(zeile);
		}
	}
		
	public static String showHex (int[] zeile) {
		StringBuffer buf		= new StringBuffer();
		for (int i : zeile) {
			String x				= Integer.toHexString(i);
			for (int j=x.length();j<8;++j)		
				x 						= 0+x;
			buf.append				(x.toLowerCase());
		}
		return					buf.toString();
	}
	
	public static String int2Hex (int i) {
		String s		= Integer.toHexString(i);
		for (int n=s.length();n<8;++n) {
			s			= "0"+s;
		}
		return s.toUpperCase();
	}

	public static String byte2Hex (byte b) {
		return int2Hex(b).substring(6);
	}
}
