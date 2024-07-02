package application;

import Project.Admin;
import Project.Group;
import Project.SystemManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewAllGroupsView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private Dimension dim;
	
	@SuppressWarnings("exports")
	public ViewAllGroupsView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.dim = dim;
		displayGUI();
	}
	
		// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
		// Creates top panel of the screen
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(0,80));
		titlePanel.setLayout(null);
		
		if (manager.getCurrentUser() instanceof Admin) {
			JButton adminBtn = new JButton("Admin Tools");
			adminBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			adminBtn.setBounds(274, 10, 152, 25);
			titlePanel.add(adminBtn);
			adminBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
		}
		
			// Option to add a login button if not already logged in
		if (manager.getCurrentUser() == null) {
			JButton btnLogin = new JButton("Login");
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogin.setBounds(129, 10, 131, 25);
			titlePanel.add(btnLogin);
			btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new LoginPopUp(manager);
				}
			});
		}
		
			// Displays current user as well as the ability to go to user profile
		if (manager.getCurrentUser() == null) {
			titlePanel.setPreferredSize(new Dimension(0,80));
			JButton btnNewUser = new JButton("Register New Account");
			btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewUser.setBounds(10, 45, 250, 25);
			titlePanel.add(btnNewUser);
			btnNewUser.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new CreateUserPopUp(manager);
				}
			});
		}
		
			// Used to update screen after login change
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new ViewAllGroupsView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnRefreshPage.setBounds(currentFrame.getBounds().width - 125, 10, 100, 25);
		titlePanel.add(btnRefreshPage);
		
			// Display labels ...
		JLabel lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUser.setBounds(10, 10, 122, 25);
		titlePanel.add(lblCurrentUser);
		
		if (manager.getCurrentUser() != null) {
			JLabel lblUid = new JLabel(manager.getCurrentUser().getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setForeground(Color.BLUE.darker());
			lblUid.setBounds(129, 10, lblUid.getPreferredSize().width+10, 25);
			lblUid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblUid.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	onViewChangeClick();
			    	new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), manager.getCurrentUser());
			    }
			});
			
			titlePanel.add(lblUid);
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		int x2 = currentFrame.getBounds().width - (btnBack.getPreferredSize().width + 35);
		btnBack.setBounds(x2, 45, btnBack.getPreferredSize().width + 10, 25);;
		titlePanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		
			// return completed panel
		return titlePanel;
	}	

	private JPanel createInsidePane() {
	
		int gridLocY = 10;							// Used to make infinite length list
		int padding = 3;							// adjust the spacing between labels
	
		ArrayList<Group> alGroup = manager.getAllGroups_Alphabetically();
		JPanel showGroupsPane = new JPanel();		// initiate panel
		showGroupsPane.setLayout(null);				// null allows use of grid coordinates to set items in the gui

			// runs through all groups and creates a label, then make the button clickable with proper location
		for (Group g : alGroup) {
			JLabel groupLabel = new JLabel(g.getGroupName() + "  -  " + manager.getCategoryByGroup(g).getName());
			groupLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			groupLabel.setForeground(Color.BLUE.darker());
			groupLabel.setBounds(25, gridLocY, groupLabel.getPreferredSize().width + 10, 25);
			groupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			groupLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	manager.setCurrentCategory(manager.getCategoryByGroup(g));
			    	manager.setCurrentGroup(g);
			    	manager.setCurrentPost(null);
			    	onViewChangeClick();
			    	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			    }
			});
			showGroupsPane.add(groupLabel);
			gridLocY += groupLabel.getSize().height + padding;			// adjust the location for the next button
			
		}
				// Not sure if needed as of now, if the list of groups gets longer, this may become useful for displaying
				//		all information
		showGroupsPane.setPreferredSize(new Dimension(currentFrame.getWidth()-50, gridLocY));
		
		return showGroupsPane;
	}
	
		// main method that creates the gui
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the listing of all groups in the system");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// whole page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
			// title panel with the buttons to log in and refresh
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
			// displays all the groups in this panel
		JPanel centerInsidePanel = createInsidePane();
		mainPanel.add(centerInsidePanel, BorderLayout.CENTER);	
		mainPanel.setSize(getPreferredSize());

			// Not necessary as of now, but this will allow the scrollbar to be added if the group list gets long
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setSize(dim);
		currentFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
			// Allow user to see the gui
		currentFrame.setVisible(true);
	}
}
