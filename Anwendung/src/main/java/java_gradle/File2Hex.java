package java_gradle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class File2Hex {

	public static void main(String[] args) {
		try {
			File ein 				= new File("C:\\Dokumente und Einstellungen\\rschneid\\Desktop\\progfun-suggestions.zip");
			File aus		 		= new File("C:\\Dokumente und Einstellungen\\rschneid\\Desktop\\progfun-suggestions.hex");
			byte[] by				= new byte[(int)ein.length()];
			FileInputStream input	= new FileInputStream(ein);
			input.read				(by);
			input.close				();
			StringBuffer buf		= new StringBuffer();
			for (byte b : by) {
				String x				= Integer.toHexString(b);
				if (x.length()<2)			x = 0+x;
				buf.append				(x.substring(x.length()-2).toUpperCase());
			}
			FileWriter out			= new FileWriter(aus);
			out.write				(buf.toString());
			out.flush				();
			out.close				();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
