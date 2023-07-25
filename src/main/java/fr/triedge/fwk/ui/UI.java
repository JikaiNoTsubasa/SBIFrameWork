package fr.triedge.fwk.ui;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class UI {

	public static void queue(Runnable runnable) {
		SwingUtilities.invokeLater(runnable);
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

	public static String askForTextInput(String title, String label, String defaultValue){
		String s = (String)JOptionPane.showInputDialog(
				null,
				label,
				title,
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				defaultValue);
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

	// https://miashs-www.u-ga.fr/prevert/Prog/Java/swing/JOptionPane.html

	public static final int OK          = 0;
	public static final int NO          = 1;
	public static final int CANCEL      = 2;

	/**
	 * Open a dialog with an error message
	 * @param parent
	 * @param title
	 * @param message
	 * @return
	 */
	public static void showError(Component parent, String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void showError(Component parent, String message){
		showError(parent, "ERROR", message);
	}

	public static void showInfo(Component parent, String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showInfo(Component parent, String message){
		showInfo(parent, "INFO", message);
	}

	public static void showMessage(Component parent, String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static void showMessage(Component parent, String message){
		showMessage(parent,"Message", message);
	}

	public static void showWarning(Component parent, String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
	}

	public static void showWarning(Component parent, String message){
		showWarning(parent,"WARN", message);
	}

	public static void showQuestion(Component parent, String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.QUESTION_MESSAGE);
	}

	public static void showQuestion(Component parent, String message){
		showQuestion(parent, "Question", message);
	}

	/**
	 * Opens a YES NO CANCEL dialog
	 * @param parent The parent component
	 * @param title The title of the window
	 * @param message The message in the window
	 * @return UI.OK or UI.NO or UI.CANCEL
	 */
	public static int showConfirm(Component parent, String title, String message){
		return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION);
	}

	public static void showConfirm(Component parent, String title, String message, UIActionListener listener){
		int res = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION);
		listener.confirm(res);
	}

	public static String showInputText(Component parent, String title, String message){
		String res = JOptionPane.showInputDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
		return (res==null||res.equals(""))?null:res;
	}

	public static void setGraphiteLookAndFeel() throws UnsupportedLookAndFeelException {
		JFrame.setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel(new SubstanceGraphiteAquaLookAndFeel());
	}
}
