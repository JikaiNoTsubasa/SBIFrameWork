package fr.triedge.fwk.ui;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class UI {

	public static void queue(Runnable runnable) {
		SwingUtilities.invokeLater(runnable);
	}
	
	public static void warn(String content) {
		JOptionPane.showMessageDialog(null,
			    content,
			    "WARNING",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public static void error(String content, Exception e) {
		ExceptionDialog ld = new ExceptionDialog(
				"Unexpected Error!",
				content, e);

		ld.setVisible(true);
	}
	
	public static void error(String content) {
		JOptionPane.showMessageDialog(null,
			    content,
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void info(String content) {
		JOptionPane.showMessageDialog(null,
			    content,
			    "INFO",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String showInputText(Component frame, String message){
		return JOptionPane.showInputDialog(frame, message);
	}
	
	public static String askForTextInput(String title, String label){
		String s = (String)JOptionPane.showInputDialog(
                null,
                label,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		return s;
	}
	
	public static byte[] imageToBytes(BufferedImage img) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( img, "png", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
	
	public static BufferedImage bytesToImage(byte[] imageData) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		return ImageIO.read(bais);
	}
}
