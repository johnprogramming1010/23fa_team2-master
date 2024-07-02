package application;
import Project.Admin;
import Project.Group;
import Project.SystemManager;
import Project.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class ProfileView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private User displayedUser;
	@SuppressWarnings("unused")
	private JTextArea aboutMe;
	
	public ProfileView() {
		
		int gridYLoc = 10;
		int padding = 10;
		
		JPanel panel = new JPanel();
		panel.setSize(300, 400);
		panel.setLayout(null);
		
		JLabel lblGroups = new JLabel(displayedUser.getId() + "'s Groups");
		lblGroups.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGroups.setBounds(10, gridYLoc, lblGroups.getPreferredSize().width + padding, 25);
		gridYLoc += lblGroups.getSize().height + padding;
		panel.add(lblGroups);
		
		for (Group g: manager.getGroupsByUser(displayedUser)) {
			JLabel lblGroupToAdd = new JLabel(g.getGroupName());
			lblGroupToAdd.setBounds(30, gridYLoc, lblGroupToAdd.getPreferredSize().width + 10, 14);
			gridYLoc += lblGroupToAdd.getSize().height + padding;
			lblGroupToAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblGroupToAdd.setForeground(Color.BLUE.darker());
			lblGroupToAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblGroupToAdd.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
	            	onViewChangeClick();
	            	manager.setCurrentCategory(manager.getCategoryByGroup(g));
	            	manager.setCurrentGroup(g);
	            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
	            }
	        });
			
			panel.add(lblGroupToAdd);
		}
	}
	
		// If no user is pushed, get currently logged in user and push it to form (null checks in place) //
	@SuppressWarnings("exports")
	public ProfileView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this(sm, jmb, frame, dim, sm.getCurrentUser());
	}

	@SuppressWarnings("exports")
	public ProfileView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, User u) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.displayedUser = u;
		this.aboutMe = new JTextArea();
		displayGUI();
	}
	
	// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();

		int gridx = 20;
		int padding = 10;
		
		titlePanel.setPreferredSize(new Dimension(0,50));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	manager.setCurrentGroup(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);

		return titlePanel;
		
	}
	
	private JPanel createGroupsJPanel() {
		
		int gridYLoc = 10;
		int padding = 10;
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		panel.setSize(350, 400);
		panel.setLayout(null);
		
		JLabel lblGroups = new JLabel(displayedUser.getId() + "'s Groups");
		lblGroups.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGroups.setBounds(10, gridYLoc, lblGroups.getPreferredSize().width + padding, 25);
		gridYLoc += lblGroups.getSize().height + padding;
		panel.add(lblGroups);
		
		for (Group g: manager.getGroupsByUser(displayedUser)) {
			JLabel lblGroupToAdd = new JLabel(manager.getCategoryByGroup(g).getName() + " - "+ g.getGroupName());
			lblGroupToAdd.setBounds(30, gridYLoc, lblGroupToAdd.getPreferredSize().width + 10, 14);
			gridYLoc += lblGroupToAdd.getSize().height + padding;
			lblGroupToAdd.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblGroupToAdd.setForeground(Color.BLUE.darker());
			lblGroupToAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblGroupToAdd.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
	            	onViewChangeClick();
	            	manager.setCurrentCategory(manager.getCategoryByGroup(g));
	            	manager.setCurrentGroup(g);
	            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
	            }
	        });
			
			panel.add(lblGroupToAdd);
		}
		
		return panel;
	}
	
	private JPanel createForm() {
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		JLabel lblProfileLabel = new JLabel("Profile");
		lblProfileLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfileLabel.setBounds(10, 10, 82, 25);
		lblProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProfileLabel);
		
		JLabel lblUserIDLabel = new JLabel("User ID:");
		lblUserIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserIDLabel.setBounds(35, 45, 110, 26);
		panel.add(lblUserIDLabel);
		
		JLabel lblNameLabel = new JLabel("Name:");
		lblNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameLabel.setBounds(35, 81, 110, 26);
		panel.add(lblNameLabel);
		
		JLabel lblCityStateLabel = new JLabel("City/State:");
		lblCityStateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCityStateLabel.setBounds(35, 117, 110, 26);
		panel.add(lblCityStateLabel);		
		
		JLabel lblBirthdayLabel = new JLabel("Birthday:");
		lblBirthdayLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBirthdayLabel.setBounds(35, 153, 110, 26);
		panel.add(lblBirthdayLabel);
		
//		JLabel lblAboutMeLabel = new JLabel("About Me:");
//		lblAboutMeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblAboutMeLabel.setBounds(35, 189, 110, 26);
//		panel.add(lblAboutMeLabel);
		
		
		// Check to see if user exist in call, if so, print user information into form //
		if (displayedUser != null) {
			if (displayedUser instanceof Admin) {
				JLabel lblAdminMark = new JLabel("(ADMIN) " + displayedUser.getId());
				lblAdminMark.setBounds(137, 45, 207, 26);
				panel.add(lblAdminMark);
			}
			else {
				JLabel lblUserId = new JLabel(displayedUser.getId());
				lblUserId.setBounds(137, 45, 207, 26);
				panel.add(lblUserId);
			}
			
			JLabel lblUserName = new JLabel(displayedUser.getName());
			lblUserName.setBounds(137, 81, 207, 26);
			panel.add(lblUserName);
			
			JLabel lblUserCity = new JLabel(displayedUser.getCity() + ", " + displayedUser.getState());
			lblUserCity.setBounds(137, 117, 207, 26);
			panel.add(lblUserCity);
			
			JLabel lblUserBDay = new JLabel(manager.getSimpleDate(displayedUser.getBirthday()));
			lblUserBDay.setBounds(137, 153, 207, 26);
			panel.add(lblUserBDay);
			
//					// May Add an About Me section to the profile.  Commented out for now. //
//			JTextArea txaAboutMe = aboutMe;
//			txaAboutMe.setLineWrap(true);
//			txaAboutMe.setFocusable(false);
//			txaAboutMe.setOpaque(false);
//			txaAboutMe.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			txaAboutMe.setBounds(35, 225, 350, 200);
//			panel.add(txaAboutMe);
//			
//			if (manager.getCurrentUser() == displayedUser) {
//				JButton btnEditAboutMe = new JButton("Edit");
//				btnEditAboutMe.setBounds(160, 189, 70, 26);
//				btnEditAboutMe.addActionListener(new ActionListener() {
//		            public void actionPerformed(ActionEvent e) {
//		      
//						txaAboutMe.setFocusable(true);
//						txaAboutMe.setOpaque(true);
//						JButton btnSave = new JButton("Save");
//						btnSave.setBounds(240, 189, 70, 26);
//						btnSave.addActionListener(new ActionListener() {
//				            public void actionPerformed(ActionEvent e) {
//				            	
//								txaAboutMe.setFocusable(false);
//								txaAboutMe.setOpaque(false);
//								
//									// FIXME: Code to save data //
//								
//								panel.remove(btnSave);
//								
//								currentFrame.repaint();
//							}
//						});
//						
//						panel.add(btnSave);
//						currentFrame.repaint();
//					}
//				});
//				panel.add(btnEditAboutMe);
//			}
		}
		JPanel usersGroups = createGroupsJPanel();
		usersGroups.setBounds(400, 0, usersGroups.getSize().width, usersGroups.getSize().height);
		panel.add(usersGroups);
		
		
		
		return panel;
	}
	
		// Build The GUI //
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Profile view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel titlePanel = createTitlePane();
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel profilePanel = createForm();
		mainPanel.add(profilePanel, BorderLayout.CENTER);

		currentFrame.setVisible(true);
	}
}
