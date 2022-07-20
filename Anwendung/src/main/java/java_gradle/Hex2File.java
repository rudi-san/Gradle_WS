package java_gradle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Hex2File {

	public static void main(String[] args) {
		try {
			File ein 				= new File("anlage.base64");
			File aus		 		= new File("anlage.txt");
			int len					= (int)ein.length();
			char[] ch				= new char[len];
			FileReader input		= new FileReader(ein);
			input.read				(ch);
			input.close				();
			byte[]	b 				= new byte[(int)ein.length()/2];
			for (int i=0;i<len;i+=2) {
				String x			= new String(Arrays.copyOfRange(ch, i, i+2));
				int y					= Byte.decode("0x0"+x.substring(0,1));
				if (y > 7) {
					int z					= 16*y+Byte.decode("0x0"+x.substring(1));
					b[i/2]					= (byte)((256-z)*-1);
				}
				else
					b[i/2]					= Byte.decode("0x"+x);
			}
			FileOutputStream out	= new FileOutputStream(aus);
			out.write				(b);
			out.flush				();
			out.close				();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
