package shoeMarket;

import javax.swing.JFrame;

public class StartLogin extends JFrame {
	LoginPanel loginPanel = new LoginPanel();

	public StartLogin() {
		this.setTitle("Login");
		this.setSize(500, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(loginPanel);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartLogin st = new StartLogin();

	}

}
