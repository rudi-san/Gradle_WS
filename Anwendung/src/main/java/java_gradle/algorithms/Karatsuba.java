package java_gradle.algorithms;

import java.util.Arrays;

public class Karatsuba {

	
	public static String multiply (String x, String y) {
		
		int len			= (x.length()>y.length()) ? x.length() : y.length();
		len				= (len%2==0) ? len : len+1;
		int half 		= len/2;
		
		x 				= stringLen(x, len);
		y 				= stringLen(y, len);
		
		String a 		= x.substring(0, half);
		String b		= x.substring(half);
		String c		= y.substring(0, half);
		String d		= y.substring(half);
			
		String p 		= add(a, b);
		String q		= add(c, d);
		
		String ac 		= "";
		String bd		= "";
		String pq		= "";
		if (half>1) {
			ac				= multiply(a, c);
			bd				= multiply(b, d);
			pq				= multiply(p, q);
		}
		else {
			ac 				= String.valueOf(Integer.parseInt(a)*Integer.parseInt(c));
			bd 				= String.valueOf(Integer.parseInt(b)*Integer.parseInt(d));
			pq 				= String.valueOf(Integer.parseInt(p)*Integer.parseInt(q));
		}
		
		String adbc		= subtract(subtract(pq, ac), bd);
				
		char[] nullen	= new char[len];
		Arrays.fill		(nullen, '0');
		String acVoll   = ac+new String(nullen);
		String adbcVoll	= adbc+new String(Arrays.copyOf(nullen, half));
		
		return 			add(add(acVoll,adbcVoll), bd);	
	}

	private static String add (String x, String y) {
		
		StringBuilder result 	= new StringBuilder();
		int uebertrag			= 0;
		int len					= Integer.max(x.length(), y.length());
		x						= stringLen(x, len);
		y						= stringLen(y, len);
		for (int i=len-1;i>=0;--i) {
			int iX				= Integer.parseInt(x.substring(i, i+1));
			int iY				= Integer.parseInt(y.substring(i, i+1));
			int sum				= iX + iY + uebertrag;
			result.insert		(0, sum%10);
			uebertrag			= sum/10;
		}
		if (uebertrag>0)
			result.insert		(0, uebertrag);
		return			result.toString();
	}

	private static String subtract (String x, String y) {
		
		StringBuilder result 	= new StringBuilder();
		int uebertrag			= 0;
		int len					= Integer.max(x.length(), y.length());
		x						= stringLen(x, len);
		y						= stringLen(y, len);
		for (int i=len-1;i>=0;--i) {
			int iX				= Integer.parseInt(x.substring(i, i+1));
			int iY				= Integer.parseInt(y.substring(i, i+1));
			int rest			= iX - iY - uebertrag;
			uebertrag			= (rest<0) ? 1 : 0;
			rest				= (rest<0) ? 10+rest : rest;
			result.insert		(0, rest);
		}
		if (uebertrag>0)
			result.insert		(0, uebertrag);
		return			result.toString();
	}
	
	private static String stringLen (String source, int len) {
		
		char[] nulls	= new char[len-source.length()];
		Arrays.fill		(nulls, '0');
		return			new String(nulls)+source;
	}
}
