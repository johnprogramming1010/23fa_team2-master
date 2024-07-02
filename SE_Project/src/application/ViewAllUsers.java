package application;

import Project.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewAllUsers extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JPanel recentActivity;
	private Group currentGroup;
	private category currentCategory;
	
	@SuppressWarnings("exports")
	public ViewAllUsers(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this(sm, jmb, frame, dim, null, null, null);
	}
	
	@SuppressWarnings("exports")
	public ViewAllUsers(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, JPanel recent, Group g, category c) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		if (recent == null) {
			recent = createRecentActivity();
		}
		this.recentActivity = recent;
		this.currentGroup = g;
		this.currentCategory = c;
		displayGUI();
	}
	
			// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
			// This creates the top panel that displays the user login info and refresh button //
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();
		int gridXLoc = 10;
		
		titlePanel.setPreferredSize(new Dimension(0,120));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(gridXLoc, 10, lblHome.getPreferredSize().width + 10, 25);
		gridXLoc += lblHome.getWidth() + 20;
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	manager.setCurrentGroup(null);
            	manager.setCurrentPost(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);
		
		JLabel lblCurrentFilter = new JLabel("Current Filter:");
		lblCurrentFilter.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentFilter.setBounds(gridXLoc, 10, lblCurrentFilter.getPreferredSize().width + 10, 25);
		gridXLoc += lblCurrentFilter.getWidth() + 20;
		titlePanel.add(lblCurrentFilter);
		
		JLabel lblCurrentFilterSeclected;
		if (currentGroup != null) {
			lblCurrentFilterSeclected = new JLabel(currentGroup.getGroupName());
		}
		else if(currentCategory != null) {
			lblCurrentFilterSeclected = new JLabel(currentCategory.getName());
		}
		else {
			lblCurrentFilterSeclected = new JLabel("No Filters");
		}
		lblCurrentFilterSeclected.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentFilterSeclected.setBounds(gridXLoc, 10, lblCurrentFilterSeclected.getPreferredSize().width + 10, 25);
		gridXLoc += lblCurrentFilterSeclected.getWidth() + 20;
		titlePanel.add(lblCurrentFilterSeclected);
		
		gridXLoc = 10;
		
		if (manager.getCurrentUser() instanceof Admin) {
			JButton adminBtn = new JButton("Admin Tools");
			adminBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			adminBtn.setBounds(274, 80, 152, 25);
			titlePanel.add(adminBtn);
			adminBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
		}
		
		JLabel lblFilterBy = new JLabel("Filter By:");
		lblFilterBy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFilterBy.setBounds(gridXLoc, 45, lblFilterBy.getPreferredSize().width + 10, 25);
		gridXLoc += lblFilterBy.getWidth() + 20;
		titlePanel.add(lblFilterBy);
		
		JLabel lblFilterByCategory = new JLabel("Category");
		lblFilterByCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFilterByCategory.setBounds(gridXLoc, 45, lblFilterByCategory.getPreferredSize().width + 10, 25);
		gridXLoc += lblFilterByCategory.getWidth() + 10;
		titlePanel.add(lblFilterByCategory);
		
		ArrayList<category> categoryArrayList = manager.getCategories_Alphabetically();
		String[] comboBoxCategoryList = new String[categoryArrayList.size()+1];
		for (int i = 0; i < categoryArrayList.size(); i++) {
			comboBoxCategoryList[i+1] = categoryArrayList.get(i).getName();
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBoxCategories = new JComboBox(comboBoxCategoryList);
		comboBoxCategories.setBounds(gridXLoc, 45, 150, 25);
		gridXLoc += comboBoxCategories.getWidth() + 20;
		comboBoxCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	currentCategory = manager.getCategoryByName(String.valueOf(comboBoxCategories.getSelectedItem()));
            	currentGroup = null;
            	JPanel update = createRecentActivity();
				onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), update, currentGroup, currentCategory);
			}
		});
		titlePanel.add(comboBoxCategories);
		
		JLabel lblFilterByGroup = new JLabel("Group");
		lblFilterByGroup.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFilterByGroup.setBounds(gridXLoc, 45, lblFilterByGroup.getPreferredSize().width + 10, 25);
		gridXLoc += lblFilterByGroup.getWidth() + 10;
		titlePanel.add(lblFilterByGroup);
		
		ArrayList<Group> categoryGroupList = manager.getAllGroups_Alphabetically();
		String[] comboBoxGroupList = new String[categoryGroupList.size()+1];
		for (int i = 0; i < categoryGroupList.size(); i++) {
			comboBoxGroupList[i+1] = categoryGroupList.get(i).getGroupName();
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBoxGroup = new JComboBox(comboBoxGroupList);
		comboBoxGroup.setBounds(gridXLoc, 45, 150, 25);
		gridXLoc += comboBoxGroup.getWidth() + 20;
		comboBoxGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	currentCategory = null;
            	currentGroup = manager.getGroupByName(String.valueOf(comboBoxGroup.getSelectedItem()));
            	JPanel update = createRecentActivity();
				onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), update, currentGroup, currentCategory);
			}
		});
		titlePanel.add(comboBoxGroup);
		
		JButton btnClearFilters = new JButton("Clear Filters");
		btnClearFilters.setBounds(gridXLoc, 45, btnClearFilters.getPreferredSize().width + 10, 25);
		gridXLoc += btnClearFilters.getWidth() + 20;
		btnClearFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel update = createRecentActivity();
            	onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), update, null, null);
            }
        });
		titlePanel.add(btnClearFilters);

		
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), recentActivity, currentGroup, currentCategory);
			}
		});
		btnRefreshPage.setBounds(currentFrame.getBounds().width - 125, 80, 100, 25);
		titlePanel.add(btnRefreshPage);
		
		JLabel lblCurrentUser = new JLabel("Current User:");
		lblCurrentUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUser.setBounds(10, 80, 122, 25);
		titlePanel.add(lblCurrentUser);
		
		if (manager.getCurrentUser() != null) {
			JLabel lblUid = new JLabel(manager.getCurrentUser().getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setForeground(Color.BLUE.darker());
			lblUid.setBounds(135, 80, lblUid.getPreferredSize().width+10, 25);
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
		else {
			new Home(manager, topBar, currentFrame, currentFrame.getSize());
		}
		
		return titlePanel;
	}
	
	private JPanel createPostBox(Post p) {
		JPanel panel = new JPanel();
		panel.setBounds(50, 38, 600, 70);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JTextArea lblTitle = new JTextArea(p.getPostTitle());
		lblTitle.setWrapStyleWord(true);
		lblTitle.setRows(2);
		lblTitle.setLineWrap(true);
		lblTitle.setFocusable(false);
		lblTitle.setOpaque(false);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(50, 5, 545, 40);
		panel.add(lblTitle);
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(manager.getCategoryByGroup(p.getGroup()));
				manager.setCurrentGroup(p.getGroup());
				manager.setCurrentPost(p);
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());					
			}
		});
		
		JLabel lblScore = new JLabel("" + p.getScore());
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(5, 27, 35, 16);
		panel.add(lblScore);
		
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, 49, 34, 13);
		panel.add(lblUidLable);
		
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(p.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + p.getUser().getId());
			lblUserId.setForeground(Color.GREEN.darker().darker());
		}
		else {
			lblUserId = new JLabel(p.getUser().getId());
			lblUserId.setForeground(Color.BLUE.darker());
		}
		lblUserId.setBounds(86, 49, lblUserId.getPreferredSize().width + 10, 13);
		lblUserId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(null);
				manager.setCurrentGroup(null);
				new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), p.getUser());					
			}
		});
		panel.add(lblUserId);
		
		JLabel lblPostedLabel = new JLabel("Posted:");
		lblPostedLabel.setBounds(410, 49, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(p.getTime()) + ", " + manager.getSimpleTime(p.getTime()));
		lblPostedDate.setBounds(460, 49, 130, 13);
		panel.add(lblPostedDate);
		
		return panel;
	}
	
	private JPanel createResponseBox(Response r) {
		Post p = manager.getPostByGroupId(r.getGroup(), r.getId());
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 38, 600, 70);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JTextArea lblTitle = new JTextArea(r.getPostBody());
		lblTitle.setWrapStyleWord(true);
		lblTitle.setRows(2);
		lblTitle.setLineWrap(true);
		lblTitle.setFocusable(false);
		lblTitle.setOpaque(false);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(50, 5, 545, 40);
		panel.add(lblTitle);
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(manager.getCategoryByGroup(r.getGroup()));
				manager.setCurrentGroup(r.getGroup());
				manager.setCurrentPost(p);
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());					
			}
		});
		
		JLabel lblScore = new JLabel("" + r.getScore());
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(5, 27, 35, 16);
		panel.add(lblScore);
		
	
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, 49, 34, 13);
		panel.add(lblUidLable);
		
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(r.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + r.getUser().getId());
		}
		else {
			lblUserId = new JLabel(r.getUser().getId());
		}
		lblUserId.setBounds(86, 49, lblUserId.getPreferredSize().width + 10, 13);
		lblUserId.setForeground(Color.BLUE.darker());
		lblUserId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(null);
				manager.setCurrentGroup(null);
				new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), r.getUser());					
			}
		});
		panel.add(lblUserId);
		
		JLabel lblPostedLabel = new JLabel("Posted:");
		lblPostedLabel.setBounds(410, 49, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(r.getTime()) + ", " + manager.getSimpleTime(r.getTime()));
		lblPostedDate.setBounds(460, 49, 130, 13);
		panel.add(lblPostedDate);
		
		return panel;
	}
	
	private JPanel createRecentActivity() {
		
		int gridYLoc = 30;
		int padding = 10;
		
		JPanel recentActivity = new JPanel();
		recentActivity.setLayout(null);
		
		JLabel userInfo = new JLabel("All Recent Activity");
		userInfo.setBounds(10,10, userInfo.getPreferredSize().width + 10, 15);
		recentActivity.add(userInfo);
		
		for (Object p : manager.viewAllUsersPostsResponses()) {
			
			if (p instanceof Response) {
				Response response = (Response) p;
				if (currentCategory != null) {
					if (currentCategory != manager.getCategoryByGroup(response.getGroup())) {
						continue;
					}
				}
				if (currentGroup != null) {
					if (currentGroup != response.getGroup()) {
						continue;
					}
				}
					
				String OPTitle = manager.getPostByGroupId(response.getGroup(), response.getParentalId()).getPostTitle();
				if (OPTitle.length() > 15) {
					OPTitle = OPTitle.substring(0,15) + " ...";
				}
				JLabel tag = new JLabel("Response to " + OPTitle + " in Group \"" + response.getGroup().getGroupName() + "\"");
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createResponseBox((Response) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			
			else if (p instanceof Post){
				Post post = (Post) p;
				if (currentCategory != null) {
					if (currentCategory != manager.getCategoryByGroup(post.getGroup())) {
						continue;
					}
				}
				if (currentGroup != null) {
					if (currentGroup != post.getGroup()) {
						continue;
					}
				}
				
				JLabel tag = new JLabel("OP in Group \"" + post.getGroup().getGroupName() + "\"");
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createPostBox((Post) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			else {
				continue;
			}
		}
		
		recentActivity.setPreferredSize(new Dimension(currentFrame.getWidth()-150, gridYLoc));
		
		return recentActivity;
	}
	
	
	private JPanel createRecentActivity(User u) {
		
		int gridYLoc = 30;
		int padding = 10;
		
		JPanel recentActivity = new JPanel();
		recentActivity.setLayout(null);
		
		JLabel userInfo = new JLabel(u.getId() + "'s Recent Activity");
		userInfo.setBounds(10,10, userInfo.getPreferredSize().width + 10, 15);
		recentActivity.add(userInfo);
		
		for (Object p : manager.viewUsersPostsResponses(u)) {
			
			if (p instanceof Response) {
				Response response = (Response) p;
				if (currentCategory != null) {
					if (currentCategory != manager.getCategoryByGroup(response.getGroup())) {
						continue;
					}
				}
				if (currentGroup != null) {
					if (currentGroup != response.getGroup()) {
						continue;
					}
				}
					
				String OPTitle = manager.getPostByGroupId(response.getGroup(), response.getParentalId()).getPostTitle();
				if (OPTitle.length() > 15) {
					OPTitle = OPTitle.substring(0,15) + " ...";
				}
				JLabel tag = new JLabel("Response to " + OPTitle + " in Group \"" + response.getGroup().getGroupName() + "\"");
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createResponseBox((Response) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			
			else if (p instanceof Post){
				Post post = (Post) p;
				if (currentCategory != null) {
					if (currentCategory != manager.getCategoryByGroup(post.getGroup())) {
						continue;
					}
				}
				if (currentGroup != null) {
					if (currentGroup != post.getGroup()) {
						continue;
					}
				}
				
				JLabel tag = new JLabel("OP in Group \"" + post.getGroup().getGroupName() + "\"");
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createPostBox((Post) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			else {
				continue;
			}
		}
		
		recentActivity.setPreferredSize(new Dimension(currentFrame.getWidth()-150, gridYLoc));
		
		return recentActivity;
		
	}
	
	
	private JPanel createUserList() {
		
		JPanel userListPanel = new JPanel();
		
		int gridYLoc = 0;
		
		ArrayList<Object> allUsers = new ArrayList<>();
		allUsers.addAll(manager.getUsers_Alphabetically());
		allUsers.addAll(manager.getAdmins_Alphabetically());
		
		for (Object user : allUsers) {
			User u = (User) user;
			JLabel lblUserName = new JLabel(u.getId());
			lblUserName.setBounds(10, gridYLoc, 130, 15);
			gridYLoc += 20;
			
			lblUserName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel update = createRecentActivity(u);
					onViewChangeClick();
					new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), update, currentGroup, currentCategory);
				}
			});
			userListPanel.add(lblUserName);
		}
		userListPanel.setPreferredSize(new Dimension(150, gridYLoc));
		
		return userListPanel;
	}	

	
		// This prints all categories as buttons on the home screen in horizontal fashion //
		// Puts the frame together, called in particular order and location, panels in panels in a frame //
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Home view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout(null);
		currentFrame.getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JPanel titlePanel = createTitlePane();
		titlePanel.setBounds(0, 0, currentFrame.getWidth(), 120);
		mainPane.add(titlePanel);
		
		JPanel userListPanel = createUserList();		// Create User listing.  Selecting user shows recent activity in JPanel Recent Activity
		userListPanel.setLayout(null);
		
		JScrollPane scrollUserListPanel = new JScrollPane(userListPanel);
		scrollUserListPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollUserListPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollUserListPanel.setBounds(0, 120, 150, currentFrame.getHeight()-165);
		mainPane.add(scrollUserListPanel);
				
		JScrollPane scrollRecentActivity = new JScrollPane(recentActivity);
		scrollRecentActivity.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRecentActivity.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollRecentActivity.setBounds(150, 120, currentFrame.getWidth() - 165, currentFrame.getHeight() - 165);
			// use invokerLater on other scroll bars!!  This fixes location after everything loads //
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
				   scrollRecentActivity.getVerticalScrollBar().setValue(0);
				   scrollRecentActivity.getVerticalScrollBar().setUnitIncrement(16);
			   }
			});
		mainPane.add(scrollRecentActivity);

		currentFrame.setVisible(true);
	}
}
