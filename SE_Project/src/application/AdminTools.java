package application;
import Project.SystemManager;
import Project.category;
import Project.Group;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminTools extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JTextField newCategoryTextField;
	private JTextField newGroupTextField;
	
			// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public AdminTools() {
	}

	@SuppressWarnings("exports")
	public AdminTools(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
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
		    	new Home(manager, topBar, currentFrame, currentFrame.getSize());
		    }
		});
		titlePanel.add(lblHome);

	return titlePanel;
			
	}
	
			// The only panel currently, can be separated into a title / main panel later if desired //
	private JPanel createMainPanel() {
		
		JPanel panel = new JPanel();		
		panel.setLayout(null);
		
		JLabel lblAdminTools = new JLabel("Admin Tools");
		lblAdminTools.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdminTools.setBounds(10, 11, 140, 25);
		panel.add(lblAdminTools);
		
		JButton btnRefreshPage = new JButton("Refresh Page");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnRefreshPage.setBounds(496, 16, 97, 23);
		panel.add(btnRefreshPage);
		
		JButton btnCreateCategory = new JButton("<html><center>"+"Create New Category"+"</center></html>");
		btnCreateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newCategoryTextField.getText() != null)
					if (manager.createCategory(newCategoryTextField.getText())) {
						JOptionPane.showMessageDialog(null, "New Category Created");
						btnRefreshPage.doClick();
					}
					else {
						JOptionPane.showMessageDialog(null, "Create Category Failed Validation");
					}
				else {
					JOptionPane.showMessageDialog(null, "Text Box Empty");
				}
			}
		});
		btnCreateCategory.setBounds(20, 47, 100, 40);
		panel.add(btnCreateCategory);
		
		newCategoryTextField = new JTextField();
		newCategoryTextField.setBounds(137, 57, 154, 20);
		panel.add(newCategoryTextField);
		newCategoryTextField.setColumns(10);
		
		ArrayList<category> categoryArrayList = manager.getCategories_Alphabetically();
		String[] comboBoxCategoryList = new String[categoryArrayList.size()];
		for (int i = 0; i < categoryArrayList.size(); i++) {
			comboBoxCategoryList[i] = categoryArrayList.get(i).getName();
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBoxCategories = new JComboBox(comboBoxCategoryList);
		comboBoxCategories.setBounds(322, 155, 154, 22);
		panel.add(comboBoxCategories);
		
		JButton btnViewAllCategories = new JButton("<html><center>View All Categories</center></html>");
		btnViewAllCategories.setBounds(20, 93, 100, 40);
		btnViewAllCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msgAllCategories = "";
				for (category c : categoryArrayList) {
					msgAllCategories += c.getName() + "\n";
				}
				JOptionPane.showMessageDialog(null, msgAllCategories);
			}
		});
		panel.add(btnViewAllCategories);
		
		newGroupTextField = new JTextField();
		newGroupTextField.setBounds(137, 155, 154, 20);
		panel.add(newGroupTextField);
		newGroupTextField.setColumns(10);
		
		JButton btnCreateGroup = new JButton("<html><center>Create New Group</center></html>");
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newGroupTextField.getText() != null) {
					if (manager.createGroup(newGroupTextField.getText(), String.valueOf(comboBoxCategories.getSelectedItem()))) {
						JOptionPane.showMessageDialog(null, "New Group Created");
						btnRefreshPage.doClick();
					}
					else {
						JOptionPane.showMessageDialog(null, "Create Group Failed Validation");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Text Box Empty or Category Not Selected");
				}
			}
		});
		btnCreateGroup.setBounds(20, 145, 100, 40);
		panel.add(btnCreateGroup);
		
		JButton btnViewAllGroups = new JButton("<html><center>View All Groups</center></html>");
		btnViewAllGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msgAllGroups = "";
				for (Group g : manager.getAllGroups_Alphabetically()) {
						msgAllGroups += g.getGroupName() + "\n";
				}
				JOptionPane.showMessageDialog(null, msgAllGroups);
			}
		});
		btnViewAllGroups.setBounds(20, 191, 100, 40);
		panel.add(btnViewAllGroups);
		
		JButton btnViewAllGroupsByCategory = new JButton("<html><center>View All Groups In Selected Category</center></html>");
		btnViewAllGroupsByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msgAllGroupsByCategory = "";
				for (Group g: manager.getGroupsInCategory_Alphabetically(manager.getCategoryByName(comboBoxCategories.getSelectedItem().toString()))) {
					msgAllGroupsByCategory += g.getGroupName() + "\n";
				}
				JOptionPane.showMessageDialog(null, msgAllGroupsByCategory);
			}
		});
		btnViewAllGroupsByCategory.setBounds(137, 191, 154, 40);
		panel.add(btnViewAllGroupsByCategory);
		
		JButton btnViewAllUsers = new JButton("<html><center>View All Users</center></html>");
		btnViewAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnViewAllUsers.setBounds(20, 237, 100, 40);
		panel.add(btnViewAllUsers);
		
		return panel;
	}
	
		// Build display, call for panels to be made //
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Admin Tools view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel titlePanel = createTitlePane();
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createMainPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
