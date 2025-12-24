// Updated Main.java to launch the GUI instead of console demo
public class Main {
	public static void main(String[] args) {
		// Launch the Main Panel GUI
		javax.swing.SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
	}
}
