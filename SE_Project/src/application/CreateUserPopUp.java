package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CreateUserPopUp extends JDialog {
	
	private SystemManager manager;
	private JFrame createUser;

	private JTextField txfName;
	private JTextField txfId;
	private JTextField txfPassword1;
	private JTextField txfPassword2;
	private JTextField txfBirthdate;
	private JTextField txfCity;
	private JTextField txfState;
	
	public CreateUserPopUp() {
	}
	
	
	public CreateUserPopUp(SystemManager manager) {
		buildGUI();
		this.manager = manager;
	}
	
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(0,50));
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Create New User");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(10, 10, 250, 25);
		titlePanel.add(lblTitle);
		
		return titlePanel;
		
	}
	
	private JPanel makeForm() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(26, 28, 141, 20);
		panel.add(lblUsername);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(26, 82, 141, 20);
		panel.add(lblName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(26, 132, 141, 20);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(26, 178, 141, 20);
		panel.add(lblConfirmPassword);
		
		JLabel lblMustMatch = new JLabel("Must Match");
		lblMustMatch.setHorizontalAlignment(SwingConstants.LEFT);
		lblMustMatch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMustMatch.setBounds(177, 196, 141, 20);
		panel.add(lblMustMatch);
		
		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBirthdate.setBounds(26, 227, 141, 20);
		panel.add(lblBirthdate);
		
		JLabel lblmmddyyyy = new JLabel("mm/dd/yyyy");
		lblmmddyyyy.setHorizontalAlignment(SwingConstants.LEFT);
		lblmmddyyyy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblmmddyyyy.setBounds(177, 244, 141, 20);
		panel.add(lblmmddyyyy);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(26, 272, 141, 20);
		panel.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(26, 320, 141, 20);
		panel.add(lblState);
		
		txfId = new JTextField();
		txfId.setBounds(177, 28, 300, 20);
		panel.add(txfId);
		txfId.setColumns(10);
		
		txfName = new JTextField();
		txfName.setColumns(10);
		txfName.setBounds(177, 82, 300, 20);
		panel.add(txfName);
		
		txfPassword1 = new JTextField();
		txfPassword1.setColumns(10);
		txfPassword1.setBounds(177, 132, 300, 20);
		panel.add(txfPassword1);
		
		txfPassword2 = new JTextField();
		txfPassword2.setColumns(10);
		txfPassword2.setBounds(177, 178, 300, 20);
		panel.add(txfPassword2);
		
		txfBirthdate = new JTextField();
//		txfBirthdate.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				// FIXME: Add date checker here //
//			}
//		});
		txfBirthdate.setColumns(10);
		txfBirthdate.setBounds(177, 226, 300, 20);
		panel.add(txfBirthdate);
		
		txfCity = new JTextField();
		txfCity.setColumns(10);
		txfCity.setBounds(177, 272, 300, 20);
		panel.add(txfCity);
		
		txfState = new JTextField();
		txfState.setColumns(10);
		txfState.setBounds(177, 320, 300, 20);
		panel.add(txfState);
		
		return panel;
	}
	
	private JPanel makeButtons() {
		JPanel btnPanel = new JPanel();
		JButton btnCreateAccount = new JButton("Create Account");
		JButton btnCancel = new JButton("Cancel");
		btnPanel.add(btnCreateAccount);
		btnPanel.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
			
		});
		
		btnCreateAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txfName.getText().isBlank() || 
				   txfId.getText().isBlank()|| 
				   txfPassword1.getText().isBlank() ||
				   txfBirthdate.getText().isBlank() || 
				   txfCity.getText().isBlank() || 
				   txfState.getText().isBlank()) {
					
					JOptionPane.showMessageDialog(null, "Ensure All Fields are filled out.");
				}
				
				else {
					if(txfPassword1.getText().equals(txfPassword2.getText())) {
						boolean status = manager.registerUser(txfName.getText(), 
															  txfBirthdate.getText(),
															  txfCity.getText(),
															  txfState.getText(),
															  txfId.getText(), 
															  txfPassword1.getText()); 
						loginMsg(status);
					}
					else {
						JOptionPane.showMessageDialog(null, "Passwords do not match.");
					}
				}
			}
		});
				// Pressing enter clicks btnSignIn //
		createUser.getRootPane().setDefaultButton(btnCreateAccount);
		return btnPanel;
	}
	
	private void loginMsg(boolean status) {
		if (status) {
			closeWindow();
		}
		else {
			JOptionPane.showMessageDialog(null, "Something didn't work...");
		}
	}
	
	private void closeWindow() {
		createUser.dispose();
	}
	
	
	
	public void buildGUI() {
		try {
			createUser = new JFrame();
		
			createUser.setDefaultCloseOperation(HIDE_ON_CLOSE);
			createUser.setSize(550, 550);
			createUser.getContentPane().setLayout(new BorderLayout(10,5)); 				//Figure out getLayout!!!//
			createUser.setLocationRelativeTo(null);
			
			createUser.getContentPane().add(createTitlePane(), BorderLayout.NORTH);
			createUser.add(makeForm(), BorderLayout.CENTER);
			createUser.getContentPane().add(makeButtons(), BorderLayout.SOUTH);
			
			createUser.setVisible(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}