package application;

import Project.Admin;
import Project.SystemManager;
import Project.User;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class UsersInGroupPopUp extends JDialog {
	private SystemManager manager;
	private JFrame listUsers;
	private JPanel usersPanel;
	private JTextField txfSuspendTime;

	public UsersInGroupPopUp(SystemManager manager) {
		this.manager = manager;
		initialize();
	}
	
		// Creates the panel that houses the list of all users
		// Admins will have ability to ban/suspend users from here
	private JPanel createUserList() {
		
		JPanel usersPanel = new JPanel();
		usersPanel.setLayout(null);
		
		int gridYLoc = 10;					// used to make infinite length list
		int padding = 3;					// adjust the gap between items
		
			// checks if group is null, displays error.  This can happen if the user changes screen while open, no refresh button, should be no issue
		if (manager.getCurrentGroup() == null) {
			JOptionPane.showMessageDialog(null, "Current Group Is NULL, check backend for issues");
			return null;
		}
		
		if (manager.getCurrentUser() instanceof Admin) {
			int gridXLoc = 10;
			JLabel lblSuspendTime = new JLabel("Amount of days to suspend user for:");
			lblSuspendTime.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblSuspendTime.setForeground(Color.BLACK);
			lblSuspendTime.setBounds(gridXLoc, gridYLoc, lblSuspendTime.getPreferredSize().width+15, 25);
			usersPanel.add(lblSuspendTime);
			
			gridXLoc += lblSuspendTime.getSize().width + 10;
			
			txfSuspendTime = new JTextField();
			txfSuspendTime.setBounds(gridXLoc, gridYLoc, 50, 25);
			usersPanel.add(txfSuspendTime);
			
			gridYLoc += txfSuspendTime.getSize().height + padding;
		}

		ArrayList<User> alUsers = manager.getUsersInGroup(manager.getCurrentGroup());

			// adds users to panel
		for (User u : alUsers) {
			
				// This section creates the username label, non-clickable because it is in a pop-up
			JLabel lblUid = new JLabel(u.getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			if (manager.isUserBannedFromGroup(u, manager.getCurrentGroup())) {
				lblUid.setForeground(Color.RED.darker().darker());
				lblUid.setText(u.getId() + " is banned forever");
				lblUid.setBounds(10, gridYLoc, lblUid.getPreferredSize().width+10, 25);
				usersPanel.add(lblUid);
				gridYLoc += lblUid.getSize().height + padding;	
				continue;
			}
							
			lblUid.setForeground(Color.BLACK);
			lblUid.setBounds(10, gridYLoc, lblUid.getPreferredSize().width+10, 25);

				// This section is for admin tools
			if (manager.getCurrentUser() instanceof Admin) {
				int gridXLoc = 10;
				JButton btnBanUser = new JButton();
				JButton btnSuspendUser = new JButton();
				
				if (manager.isUserBannedFromGroup(u, manager.getCurrentGroup())) {
					btnBanUser = new JButton("User Banned");
					btnBanUser.setFont(new Font("Tahoma", Font.BOLD, 15));
					btnBanUser.setBounds(gridXLoc, gridYLoc, btnBanUser.getPreferredSize().width + 10, 25);
				}
				else {
					btnBanUser = new JButton("Ban");
					btnBanUser.setFont(new Font("Tahoma", Font.BOLD, 15));
					btnBanUser.setBounds(gridXLoc, gridYLoc, btnBanUser.getPreferredSize().width + 10, 25);
						// TODO: Need a verification pop-up.  This can not be undone
					btnBanUser.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
					        int input = JOptionPane.showConfirmDialog(null, "This can not be undone, BAN user forever?", "Game-Over Dude?",
									JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
							
					        // 0=yes, 1=no
					        if (input == 0) {
    	
				    			manager.banUser(u, manager.getCurrentGroup());
				    			listUsers.dispose();
				    			new UsersInGroupPopUp(manager);
				    			JOptionPane.showMessageDialog(null, "User is banned forever!");
				    			
					        }

						}
					});
				}
				gridXLoc += btnBanUser.getSize().width + 5;
				
				if (manager.isUserSuspendedFromGroup(u, manager.getCurrentGroup())) {
					lblUid.setForeground(Color.RED.darker().darker());
					lblUid.setText(u.getId() + " is suspended until " + manager.getSuspensionEndDate(manager.getSuspensions_ByUsernameGroup(u, manager.getCurrentGroup())));
					btnSuspendUser = new JButton("Cancel Suspension");
					btnSuspendUser.setFont(new Font("Tahoma", Font.BOLD, 15));
					btnSuspendUser.setBounds(gridXLoc, gridYLoc, btnSuspendUser.getPreferredSize().width + 10, 25);
					btnSuspendUser.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	manager.reinstateSuspendedUser(manager.getSuspensions_ByUsernameGroup(u, manager.getCurrentGroup()));
			            	listUsers.dispose();
			            	new UsersInGroupPopUp(manager);
			            }
					});
				}
				else  {
					btnSuspendUser = new JButton("Suspend");
					btnSuspendUser.setFont(new Font("Tahoma", Font.BOLD, 15));
					btnSuspendUser.setBounds(gridXLoc, gridYLoc, btnSuspendUser.getPreferredSize().width + 10, 25);
					btnSuspendUser.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	int days;
			            	
			            	if (txfSuspendTime.getText().isBlank()) {
			            		JOptionPane.showMessageDialog(null, "Please enter the amount of time to suspend the user");
			            	}
			            	else {
				            	try {
				            		days = Integer.parseInt(txfSuspendTime.getText());
				            		manager.suspendUser(u, manager.getCurrentGroup(), days);
				            	} catch (Exception ex){
				            		JOptionPane.showMessageDialog(null, "Make sure days enter is an integer");
				            		System.out.print(ex);
				            	}
			            	}
			            	listUsers.dispose();
			            	new UsersInGroupPopUp(manager);
						}
					});
				}
				gridXLoc += btnSuspendUser.getSize().width + 5;
				
					// move the previous tag over to make space for ban and suspend buttons
				lblUid.setBounds(gridXLoc, gridYLoc, lblUid.getPreferredSize().width+10, 25);
				usersPanel.add(btnBanUser);
				usersPanel.add(btnSuspendUser);
				
			}
			
			usersPanel.add(lblUid);
				// adjust YLoc to next location
			gridYLoc += lblUid.getSize().height + padding;	
		}
		
		
		return usersPanel;
	}
	
		// Main method that creates, builds and displays gui
	private void initialize() {

		listUsers = new JFrame();
		listUsers.setDefaultCloseOperation(HIDE_ON_CLOSE);
		listUsers.setTitle("All users in this group");
		listUsers.setLayout(null);
		listUsers.setLocationRelativeTo(null);
		listUsers.setSize(350, 500);
		
		if (manager.getCurrentUser() instanceof Admin) {
			listUsers.setSize(750, 500);
		}
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
		usersPanel = createUserList();		
		mainPanel.add(usersPanel, BorderLayout.CENTER);	
		mainPanel.setSize(getPreferredSize());
		
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setSize(listUsers.getSize().width-15, listUsers.getSize().height-36);
		
		listUsers.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		listUsers.setVisible(true);
	}
}