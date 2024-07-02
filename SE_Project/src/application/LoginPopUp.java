package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginPopUp extends JDialog {
	private JTextField txfName;
	private JTextField txfPass;
	private SystemManager manager;
	private JFrame login;

	public LoginPopUp(SystemManager manager) {
		initialize();
		this.manager = manager;
	}
	
	public void initialize() {
		try {
			login = new JFrame();
		
			login.setDefaultCloseOperation(HIDE_ON_CLOSE);
			login.setSize(300, 150);
			login.setLayout(new BorderLayout(10,5)); 				//Figure out getLayout!!!//
			login.setLocationRelativeTo(null);
			login.add(makeButtons(), BorderLayout.SOUTH);
			login.add(makeUser(), BorderLayout.NORTH);
			login.add(makePass(), BorderLayout.CENTER);

			login.setVisible(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel makeUser() {
		JPanel username = new JPanel();
		JLabel lblName = new JLabel("Username:");
		txfName = new JTextField(15);
		username.add(lblName);
		username.add(txfName);
		return username;
	}

	private JPanel makePass() {
		JPanel password = new JPanel();
		JLabel lblPass = new JLabel("Password:");
		txfPass = new JTextField(15);
		password.add(lblPass);
		password.add(txfPass);

		return password;
	}
	
	private JPanel makeButtons() {
		JPanel btnPanel = new JPanel();
		JButton btnSignIn = new JButton("Sign in");
		JButton btnGuest = new JButton("Continue as Guest");
		btnPanel.add(btnSignIn);
		btnPanel.add(btnGuest);
		
		btnGuest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
			
		});
		
		btnSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txfName.getText().isBlank() || txfPass.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Username or Password is Blank");
				}
				else {
					boolean status = manager.login(txfName.getText(), txfPass.getText());
					loginMsg(status);
				}
			}
		});
				// Pressing enter clicks btnSignIn //
		login.getRootPane().setDefaultButton(btnSignIn);
		return btnPanel;
	}
	
	private void loginMsg(boolean status) {
		if (status) {
			closeWindow();
		}
		else {
			JOptionPane.showMessageDialog(null, "Incorrect username or pass");
		}
	}
	
	private void closeWindow() {
		login.dispose();
	}
	
}