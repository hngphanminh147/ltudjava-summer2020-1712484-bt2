package app;
import java.awt.EventQueue;
import ui.LoginForm;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm loginForm = new LoginForm();
					loginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
