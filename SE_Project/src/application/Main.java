package application;

import Project.SystemManager;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private static JMenuBar topBar;
	private static SystemManager manager;
	private JFrame currentFrame;
	private ArrayList<String> fileNames = new ArrayList<String>();
	
	public Main() {
	}
	
	// Creates the menu bar //
	private JMenuBar createMenus() {
				//Bar that holds Menus//
		JMenuBar menus = new JMenuBar();
				//Menus//
//		JMenu refresh = new JMenu("Refresh");
				//Sub-menus//
		JMenuItem login = new JMenuItem("Login/Logout");
		JMenuItem switchHome = new JMenuItem("Home");
		JMenuItem switchProfile = new JMenuItem("Profile");
				//Add menus to bar//
//		menus.add(refresh); // Add if currentView is added.
				//Add sub-menus to menus//
		menus.add(login);
		menus.add(switchProfile);
		menus.add(switchHome);
		
		login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (manager.getCurrentUser() == null) {
            		new LoginPopUp(manager);
            	}
            	else {
			        int input = JOptionPane.showConfirmDialog(null, "Logout?", "Confirm Choice",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

			        // 0=yes, 1=no
			        if (input == 0) {
            			manager.logout();	
            			JOptionPane.showMessageDialog(null, "Logout Successful");
			        }
            	}
            }
        });

		switchHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	manager.setCurrentGroup(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		
		switchProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (manager.getCurrentUser() != null) {
	            	onViewChangeClick();
	            	new ProfileView(manager, topBar, currentFrame, currentFrame.getSize());
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "User is not logged in");
            	}
            }
        });
				
		return menus;
	}
	
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private void init() {
		
		if (manager == null) {
			startMemory();
		}
		new Home(manager, topBar, currentFrame, currentFrame.getSize());
	}
	
	private void startMemory() {
		
		/*
		 * Files need to be read/added in a specific order:
		 * 0. Admins
		 * 1. Users
		 * 2. Categories
		 * 3. Groups
		 * 4. Memberships
		 * 5. Posts
		 * 6. Responses
		 * 7. Voted
		 * 8. Suspended
		 * 9. Banned
		 */
		
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Admins.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Users.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Categories.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Groups.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Memberships.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Posts.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Responses.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Voted.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Suspended.txt");
		fileNames.add(".\\SE_Project\\src\\TextFiles\\Banned.txt");

		
		
		manager = new SystemManager(fileNames);
		topBar = createMenus();
		currentFrame = new JFrame();
		currentFrame.setSize(800,800);
	}
	
	
				// Main //
	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main().init();
            }
        });
	}
}
