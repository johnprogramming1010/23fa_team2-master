package application;
import Project.Group;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class CategoryView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private final int maxHeight = 10;
	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public CategoryView() {
	}
	
	@SuppressWarnings("exports")
	public CategoryView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
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

		int gridx = 10;
		int padding = 10;
		
		titlePanel.setPreferredSize(new Dimension(0,50));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);
		
		JLabel lblSpacer1 = new JLabel("//");
		lblSpacer1.setBounds(gridx, 10, 10, 25);
		gridx += lblSpacer1.getWidth() + padding;
		titlePanel.add(lblSpacer1);
		
		JLabel lblCurrentCategory = new JLabel(manager.getCurrentCategory().getName());
		lblCurrentCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCurrentCategory.setBounds(gridx, 10, lblCurrentCategory.getPreferredSize().width + padding, 25);
		gridx += lblCurrentCategory.getWidth() + padding;
		titlePanel.add(lblCurrentCategory);
		
		return titlePanel;
		
	}
	
	private JPanel createGridPane() {
		
		JPanel groupGridPane = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		groupGridPane.setLayout(gbl);
		
		ArrayList<Group> alGroups = manager.getGroupsInCategory_Alphabetically(manager.getCurrentCategory());
		
		for (int i = 0, j = 0; i < alGroups.size(); i++) {
			if (i % maxHeight == 0) {
				j++;
			}
			
			Group currentGroup = alGroups.get(i);
			double weightY = 0.0;
			if (i == maxHeight - 1 || (i == alGroups.size() - 1 && i < maxHeight - 1)) {
				weightY = 1;
			}
			
			
			JButton button = new JButton(alGroups.get(i).getGroupName());
			GridBagConstraints gbc = new GridBagConstraints(
		            j, (i % maxHeight),             //cell x , y
		            1, 1,                           //cell width , cell height
		            1, 								//weightx
		            weightY,			            //weighty
		            GridBagConstraints.PAGE_START,  //where to anchor the component in the cell
		            GridBagConstraints.HORIZONTAL,  //how to fill extra space
		            new Insets(0, 0, 0, 0),         //insets for the cell
		            0, 1);                          //additional padding x,y
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					manager.setCurrentGroup(currentGroup);
					new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
			groupGridPane.add(button, gbc);
		}
		
		return groupGridPane;
	}
	
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Category view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
		JPanel centerInsidePanel = createGridPane();
		mainPanel.add(centerInsidePanel, BorderLayout.CENTER);		
		
		currentFrame.setVisible(true);
	}
}
