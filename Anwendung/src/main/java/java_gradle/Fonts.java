package java_gradle;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fonts {

	public static void main(String[] args) {

		String fonts[] 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int len				= fonts.length;

		JFrame frame		= new JFrame("FFFFoooonnnnttttssss");
		JPanel panel		= new JPanel(new GridLayout(len,1));
	    for (String font : fonts ) {
	    	JPanel textPanel	= new JPanel(new FlowLayout(FlowLayout.LEFT));
	    	textPanel.add		(new JLabel(font));
	    	JTextField text		= new JTextField();
	    	text.setFont		(new Font(font, Font.PLAIN, 20));
			text.setText		("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789");
			textPanel.add		(text);
			panel.add			(textPanel);
	    }
	    frame.getContentPane().add	(new JScrollPane(panel));
	    frame.setLocationRelativeTo	(null);
	    frame.setSize				(800, 800);
	    frame.setVisible			(true);
	}

}
