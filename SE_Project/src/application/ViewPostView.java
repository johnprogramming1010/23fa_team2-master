package application;
import Project.*;	// Needed for instanceof and comparing items

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewPostView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JTextArea txfPostBody;
	private Dimension dim;

		
	@SuppressWarnings("exports")
	public ViewPostView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
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
	
		// All the information at the top of the screen below the post is shown in the titlepane
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();

		int gridx = 20;										// used for determining the left/right orientation
		int padding = 10;									// Change this to make the gaps between items larger
		
		titlePanel.setPreferredSize(new Dimension(0,80));	// Defaults to correct width, change second number to make title bar taller
		titlePanel.setLayout(null);							// Allows use of grid points to setup screen
		
			// Labels are mostly similar, set label, font, color, location
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	// creates hand when hovering over item
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);							// build button before adding it to the Panel
		
		JLabel lblSpacer1 = new JLabel("//");
		lblSpacer1.setBounds(gridx, 10, 10, 25);
		gridx += lblSpacer1.getWidth() + padding;
		titlePanel.add(lblSpacer1);
		
		JLabel lblCurrentCategory = new JLabel(manager.getCurrentCategory().getName());
		lblCurrentCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCurrentCategory.setForeground(Color.BLUE.darker());
		lblCurrentCategory.setBounds(gridx, 10, lblCurrentCategory.getPreferredSize().width + padding, 25);
		gridx += lblCurrentCategory.getWidth() + padding;
		lblCurrentCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCurrentCategory.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentGroup(null);
            	new CategoryView(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblCurrentCategory);
		
		JLabel lblSpacer2 = new JLabel("//");
		lblSpacer2.setBounds(gridx, 10, 10, 25);
		gridx += lblSpacer2.getWidth() + padding;
		titlePanel.add(lblSpacer2);
		
		JLabel lblCurrentGroup = new JLabel(manager.getCurrentGroup().getGroupName());
		lblCurrentGroup.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCurrentGroup.setForeground(Color.BLUE.darker());
		lblCurrentGroup.setBounds(gridx, 10, lblCurrentGroup.getPreferredSize().width + padding, 25);
		lblCurrentGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		titlePanel.add(lblCurrentGroup);
		lblCurrentGroup.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		int x1 = currentFrame.getBounds().width - (btnRefreshPage.getPreferredSize().width + padding + 50);
		btnRefreshPage.setBounds(x1, 10, btnRefreshPage.getPreferredSize().width + padding, 25);
		titlePanel.add(btnRefreshPage);
		
		//	SECOND ROW OF LABLES //
		gridx = 20;											// resets gridx back to 20
			// checks if user is logged in, if not, show login, otherwise, give login information
		if (manager.getCurrentUser() == null){

			JButton btnLogin = new JButton("Login");
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogin.setBounds(gridx, 45, btnLogin.getPreferredSize().width + padding, 25);
			gridx += btnLogin.getWidth() + padding;
			titlePanel.add(btnLogin);
			btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new LoginPopUp(manager);
				}
			});
			
			JButton btnNewUser = new JButton("Register New Account");
			btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewUser.setBounds(gridx, 45, btnNewUser.getPreferredSize().width + padding, 25);
			gridx += btnNewUser.getWidth() + padding;
			titlePanel.add(btnNewUser);
			btnNewUser.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new CreateUserPopUp(manager);
				}
			});

		}
			// Checks if member of group, if not, give option to join group
		else if (!manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			
			JLabel memberStatus = new JLabel("Only Members Can Post In Group");
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(gridx, 45, memberStatus.getPreferredSize().width + padding + padding, 25);
			gridx += memberStatus.getWidth() + padding;
			titlePanel.add(memberStatus);
			
			JLabel joinGroup = new JLabel("Join This Group");
			joinGroup.setFont(new Font("Tahoma", Font.BOLD, 15));
			joinGroup.setForeground(Color.BLUE.darker());
			joinGroup.setBounds(gridx, 45, joinGroup.getPreferredSize().width + padding, 25);
			gridx += joinGroup.getWidth() + padding;
			joinGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			joinGroup.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	boolean result = manager.joinGroup(manager.getCurrentUser(), manager.getCurrentGroup());
			    	if (result) {
			    		JOptionPane.showMessageDialog(null, "Successfully Joined Group");
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Something Went Wrong");
			    	}
			    	btnRefreshPage.doClick();
	            }
	        });
			titlePanel.add(joinGroup);
		}
		
			// General information
		else {
			String mbmSince = "Member Since: " + manager.getMembership(manager.getCurrentGroup(), manager.getCurrentUser()).getDate().toString();

			JLabel memberStatus = new JLabel(mbmSince);
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(gridx, 45, memberStatus.getPreferredSize().width + padding + padding, 25);
			gridx += memberStatus.getWidth() + padding;
			titlePanel.add(memberStatus);
		}
		

		
			// May remove, not really needed anymore, this was an original need with old setup.
		JButton btnBack = new JButton("Back to " + manager.getCurrentGroup().getGroupName());	// If reaching by another screen, says where back goes to
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		int x2 = currentFrame.getBounds().width - (btnBack.getPreferredSize().width + padding + 50);
		btnBack.setBounds(x2, 45, btnBack.getPreferredSize().width + padding, 25);;
		titlePanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
	
		return titlePanel;									// Panel complete, return
		
	}
	
		// Main Post Creation at top of screen 
	private JPanel createPostViewForm() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		int gridy = 10;
		int padding = 10;
		
			// fail safe, probably need to add a return statement to this to prevent building further
		if (manager.getCurrentPost() != null) {
		
			JLabel lblTitle = new JLabel(manager.getCurrentPost().getPostTitle());
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTitle.setBounds(40, gridy + 10, 560 , lblTitle.getPreferredSize().height + padding);
			gridy += lblTitle.getHeight() + padding;
			panel.add(lblTitle);
			
			JTextArea textArea = new JTextArea(manager.getCurrentPost().getPostBody());
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textArea.setBounds(40, gridy + 10, 560, textArea.getPreferredSize().height + padding);
			gridy += textArea.getHeight() + padding;
			textArea.setEditable(false);
			panel.add(textArea);
		}
		
		JLabel lblScore = new JLabel("" + manager.getCurrentPost().getScore());
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 27, 20, 10);
		panel.add(lblScore);
		
		try {
			BufferedImage upArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\BlankUpArrow.png"));
			if (manager.getCurrentUser() != null && manager.votedPostExists(manager.getCurrentPost()) && manager.hasUpvotedPost(true, manager.getCurrentPost())) {
				upArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\UpArrow.png"));
			}
			JButton btnUpVote = new JButton(new ImageIcon(upArrow));
			btnUpVote.setBounds(10, 5, 20, 22);
			btnUpVote.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if (manager.getCurrentUser() != null) {
                        manager.upvotePost(manager.getCurrentPost());
        				onViewChangeClick();
        				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You must login to vote");
                    }
	            }
			});
			panel.add(btnUpVote);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			BufferedImage downArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\BlankDownArrow.png"));
			if (manager.getCurrentUser() != null && manager.votedPostExists(manager.getCurrentPost()) && manager.hasDownvotedPost(true, manager.getCurrentPost())) {
				downArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\DownArrow.png"));
			}
			JButton btnDownVote = new JButton(new ImageIcon(downArrow));
			btnDownVote.setBounds(10, 37, 20, 22);
			btnDownVote.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if (manager.getCurrentUser() != null) {
                        manager.downvotePost(manager.getCurrentPost());
        				onViewChangeClick();
        				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You must login to vote");
                    }
	            	
				}
			});
			panel.add(btnDownVote);
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
			// Builds across the bottom of the panel with user and post time information
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, gridy, 34, 13);
		panel.add(lblUidLable);
			
			// Adjust color of the label based on who the user is.  Admins are dark green
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(manager.getCurrentPost().getUser())) {
			lblUserId = new JLabel("(ADMIN) " + manager.getCurrentPost().getUser().getId());
			lblUserId.setForeground(Color.GREEN.darker().darker());
		}
		else {
			lblUserId = new JLabel(manager.getCurrentPost().getUser().getId());
			lblUserId.setForeground(Color.BLUE.darker());
		}
		lblUserId.setBounds(86, gridy, lblUserId.getPreferredSize().width + 10, 13);
		lblUserId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(null);
				manager.setCurrentGroup(null);
				new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), manager.getCurrentPost().getUser());					
			}
		});
		panel.add(lblUserId);
		
		JLabel lblPostedLabel = new JLabel("Posted:");
		lblPostedLabel.setBounds(410, gridy, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(manager.getCurrentPost().getTime()) + ", " + manager.getSimpleTime(manager.getCurrentPost().getTime()));
		lblPostedDate.setBounds(460, gridy, 130, 13);
		panel.add(lblPostedDate);
		
		gridy += 18;
		
			// prevents banned user from posting
		if (manager.isUserBannedFromGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			JLabel memberStatus = new JLabel("You are banned from this group!");
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(60, gridy, 416, 124);
			gridy += memberStatus.getHeight() + padding;
			panel.add(memberStatus);
		}
		
			// prevents suspended user from posting
		else if (manager.isUserSuspendedFromGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			JLabel memberStatus = new JLabel("You are suspended until " + manager.getSuspensionEndDate(manager.getSuspensions_ByUsernameGroup(manager.getCurrentUser(), manager.getCurrentGroup())));
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(60, gridy, 416, 124);
			gridy += memberStatus.getHeight() + padding;
			panel.add(memberStatus);
		}
				// if member of the group, show response box
		else if (manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())){	
			txfPostBody = new JTextArea();
			txfPostBody.setColumns(10);
			JScrollPane scrollPane= new JScrollPane(txfPostBody);
			scrollPane.setBounds(50, gridy, 440, 130);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel.add(scrollPane);		
				
			JButton btnRespond = new JButton("Respond");
			btnRespond.setBounds(500, gridy, 100, 130);
			btnRespond.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
			    	if (manager.createNewResponse(manager.getCurrentGroup(), txfPostBody.getText(), manager.getCurrentPost())) {
			    		onViewChangeClick();
						new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			        }
	            	else {
	            		JOptionPane.showMessageDialog(null, "An error occured");
	            	}
				}
			});
			gridy += scrollPane.getHeight() + padding;
			panel.add(btnRespond);
		}
		
				// Add Flag to the main post only if user is an admin
				// Tried to add these two files into the main file so only loads once, but would not appear when I did that
				//		I suspect this is due to the fact that all labels were technically the same item.
		if(manager.getCurrentUser() instanceof Admin) {
			JLabel picLabel = new JLabel();						// Establish button
			try {
				if (manager.getCurrentPost().getFlag()) {
					BufferedImage isFlagPic;
					isFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmall.png"));
					JLabel isFlaggedPic = new JLabel(new ImageIcon(isFlagPic));
					picLabel = isFlaggedPic;
				}
				else {
					BufferedImage notFlagPic;
					notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
					JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
					picLabel = notFlaggedPic;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			picLabel.setBounds(600, 20, 25, 25);			// Location of the flag in the panel
			picLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (manager.getCurrentPost().getFlag()) {
			    		manager.removeFlagOnPost(manager.getCurrentPost());
			    		onViewChangeClick();
			    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			    	}
			    	else {
			    		manager.flagPost(manager.getCurrentPost());
			    		onViewChangeClick();
			    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			    	}
			    }
		    });
			panel.add(picLabel);
			
			JLabel picRedXLabel = new JLabel();						// Establish button
			try {
					BufferedImage deletePic;
					deletePic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedX.png"));
					JLabel redXPic = new JLabel(new ImageIcon(deletePic));
					picRedXLabel = redXPic;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			picRedXLabel.setBounds(635, 20, 25, 25);			// Location of the flag in the panel
			picRedXLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        int input = JOptionPane.showConfirmDialog(null, "This can not be undone! Delete this post and all of it's responses?", "Confirm Choice",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

			        // 0=yes, 1=no
			        if (input == 0) {
			        	manager.deleteNewPost(manager.getCurrentPost());
			        	
		    			JOptionPane.showMessageDialog(null, "Post has been terminated!");
		    			onViewChangeClick();
		    			manager.setCurrentPost(null);
		    			new GroupView(manager, topBar, currentFrame, currentFrame.getSize());

			        }
			    }
		    });
			panel.add(picRedXLabel);
		}
		
				// Add Flag to the main post only if user
		if(manager.getCurrentUser() instanceof User) {
			JLabel picLabel = new JLabel();						// Establish button
			try {
				BufferedImage notFlagPic;
				notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
				JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
				picLabel = notFlaggedPic;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			picLabel.setBounds(600, 20, 25, 25);			// Location of the flag in the panel
			picLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        int input = JOptionPane.showConfirmDialog(null, "Do you want flag this post for admin to review?", "Select an Option...",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					
			        // 0=yes, 1=no
			        if (input == 0) {
			        	manager.getCurrentPost().setFlagTrue();
		    			JOptionPane.showMessageDialog(null, "Message has been flagged for admin to see");
			        }

			    }
		    });
			panel.add(picLabel);
		}
		

			// panel size, Not sure how gridy is the height, but it is.
		panel.setPreferredSize(new Dimension(currentFrame.getWidth(), gridy));
		
		
		return panel;
	}
	
		// Create an individual box for a response
	private JPanel createResponseBox(Response r) {
		JPanel panel = new JPanel();
		int gridYLoc = 5;
		
		panel.setBounds(0, 0, 600, 100); // Needs to be adjusted based on size of text ... does this at the end of the panel, not sure if this is needed anymore
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEtchedBorder());			// Change the type of border for each response here
		
		JTextArea txResponseBox = new JTextArea(r.getPostBody());
		txResponseBox.setWrapStyleWord(true);
		txResponseBox.setLineWrap(true);
		txResponseBox.setFocusable(true);								// allows copy/paste
		txResponseBox.setOpaque(false);
		txResponseBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txResponseBox.setSize(getPreferredSize());						// set size first, then setbounds.  otherwise will get wonkey
		txResponseBox.setBounds(40, gridYLoc, 545, txResponseBox.getPreferredSize().height);
		gridYLoc += txResponseBox.getHeight() + 20;						// moves grid setpoint
		panel.add(txResponseBox);
			
			// Self explanatory on the names of the labels and buttons here
		JLabel lblScore = new JLabel("" + r.getScore());
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 27, 20, 10);
		panel.add(lblScore);
		
		try {
			BufferedImage upArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\BlankUpArrow.png"));
			if (manager.getCurrentUser() != null && manager.votedResponseExists(r) && manager.hasUpvotedResponse(r, false)) {
				upArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\UpArrow.png"));
			}
			JButton btnUpVote = new JButton(new ImageIcon(upArrow));
			btnUpVote.setBounds(10, 5, 20, 22);
			btnUpVote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	            	if (manager.getCurrentUser() != null) {
                        manager.upvoteResponse(r);
                        onViewChangeClick();
        				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You must login to vote");
                    }
	            }
			});
			panel.add(btnUpVote);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			BufferedImage downArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\BlankDownArrow.png"));
			if (manager.getCurrentUser() != null && manager.votedResponseExists(r) && manager.hasDownvotedResponse(r, false)) {
				downArrow = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\DownArrow.png"));
			}
			JButton btnDownVote = new JButton(new ImageIcon(downArrow));
			btnDownVote.setBounds(10, 37, 20, 22);
			btnDownVote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (manager.getCurrentUser() != null) {
                        manager.downvoteResponse(r);
                        onViewChangeClick();
        				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You must login to vote");
                    }
				}
			});
			panel.add(btnDownVote);
				
		} catch (Exception e) {
			System.out.println(e);
			    
		}

			// Builds across the bottom of the panel with user and post time information
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, gridYLoc, 34, 13);
		panel.add(lblUidLable);
			
			// Adjust color of the label based on who the user is.  Admins are dark green
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(r.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + r.getUser().getId());
			lblUserId.setForeground(Color.GREEN.darker().darker());
		}
		else {
			lblUserId = new JLabel(r.getUser().getId());
			lblUserId.setForeground(Color.BLUE.darker());
		}
		lblUserId.setBounds(86, gridYLoc, lblUserId.getPreferredSize().width + 10, 13);
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
		lblPostedLabel.setBounds(410, gridYLoc, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(r.getTime()) + ", " + manager.getSimpleTime(r.getTime()));
		lblPostedDate.setBounds(460, gridYLoc, 130, 13);
		panel.add(lblPostedDate);
		
		panel.setBounds(0, 0, 600, Math.max(gridYLoc, 50));		// Minimum height of box is 50, that is why this is looking for max between the desired size and 40
		
		return panel;
	}
	
	
		// create the entire pane below the post.  This cycles all responses and adds them to the page.
	private JPanel createResponsesPane() {
		
		int gridLocY = 10;										// Used to expand the panel to infinite length
		int padding = 20;										// change this to change distances between items

		ArrayList<Response> alResponse = manager.viewAllPostResponses(manager.getCurrentPost());
		JPanel responsePane = new JPanel();
		responsePane.setLayout(null);

		for (Response r : alResponse) {
			JPanel responseBox = createResponseBox(r);
			responseBox.setBounds(40, gridLocY, responseBox.getSize().width + padding, responseBox.getSize().height + padding);
			

			// As before, only admins set flags on or off
			if(manager.getCurrentUser() instanceof Admin) {
				JLabel picLabel = new JLabel();
				try {
					if (r.getFlag()) {
						BufferedImage isFlagPic;
						isFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmall.png"));
						JLabel isFlaggedPic = new JLabel(new ImageIcon(isFlagPic));
						picLabel = isFlaggedPic;
					}
					else {
						BufferedImage notFlagPic;
						notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
						JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
						picLabel = notFlaggedPic;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				picLabel.setBounds(10, gridLocY, 25, 25);		// Flag Location
				
					// Add ability to change the value of a flag, then refresh the screen
				picLabel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if (r.getFlag()) {
				    		r.setFlagFalse();
				    		onViewChangeClick();
				    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				    	}
				    	else {
				    		r.setFlagTrue();
				    		onViewChangeClick();
				    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				    	}
				    }
			    });
				responsePane.add(picLabel);
				
					// Create a red x next to every response.  clicking it will allow the admin to delete response content
				JLabel redXLabel = new JLabel();
				try {
					BufferedImage redXPic;
					redXPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedX.png"));
					JLabel deletePic = new JLabel(new ImageIcon(redXPic));
					redXLabel = deletePic;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				redXLabel.setBounds(10, gridLocY + 35, 25, 25);		// Flag Location
				
					// Add ability to change the value of a flag, then refresh the screen
				redXLabel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        int input = JOptionPane.showConfirmDialog(null, "This can not be undone! Delete this post and all of it's responses?", "Confirm Choice",
								JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				        // 0=yes, 1=no
				        if (input == 0) {
				        	String userTitle = "Admin";
				        	manager.removeResponseToPost(manager.getCurrentPost(), r, userTitle);
				        	JOptionPane.showMessageDialog(null, "Response has been terminated!");
				        	onViewChangeClick();
				        	new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				        }
				    }
			    });
				responsePane.add(redXLabel);
			}
			

			// As before, Users can flag responses, but can not edit the same flags.
			if(manager.getCurrentUser() instanceof User) {
				JLabel picLabel = new JLabel();
				try {
					BufferedImage notFlagPic;
					notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
					JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
					picLabel = notFlaggedPic;
        } catch (IOException e) {
					e.printStackTrace();
				}
        picLabel.setBounds(10, gridLocY, 25, 25);		// Flag Location
        					// Add ability to change the value of a flag to true
				picLabel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        int input = JOptionPane.showConfirmDialog(null, "Do you want flag this post for admin to review?", "Select an Option...",
								JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						
				        // 0=yes, 1=no
				        if (input == 0) {
				        	r.setFlagTrue();
			    			  JOptionPane.showMessageDialog(null, "Message has been flagged for admin to see");
				        }
				    }
			    });
				responsePane.add(picLabel);
			}
        

			if (manager.getCurrentUser() == r.getUser()) {
				// Create a red x next to every response.  clicking it will allow the owning user to delete response content
				JLabel redXLabel = new JLabel();
				try {
					BufferedImage redXPic;
					redXPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedX.png"));
					JLabel deletePic = new JLabel(new ImageIcon(redXPic));
					redXLabel = deletePic;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				redXLabel.setBounds(10, gridLocY + 35, 25, 25);		// Flag Location
				
					// Add ability to change the value of a flag, then refresh the screen
				redXLabel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        int input = JOptionPane.showConfirmDialog(null, "This can not be undone! Delete this post and all of it's responses?", "Confirm Choice",
								JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	
				        // 0=yes, 1=no
				        if (input == 0) {
				        	String userTitle = "Author";
				        	manager.removeResponseToPost(manager.getCurrentPost(), r, userTitle);
				        	JOptionPane.showMessageDialog(null, "Response has been terminated!");
				        	onViewChangeClick();
				        	new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				        }
				    }
			    });
				responsePane.add(redXLabel);
			}
			
			gridLocY += responseBox.getHeight() + padding;			
			responsePane.add(responseBox);
		}
		responsePane.setPreferredSize(new Dimension(currentFrame.getWidth(), gridLocY));
		return responsePane;
	}
	
		// Main method, build and display gui
	private void displayGUI() {
		
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Post view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// main panel is the entire page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
			// topInsidePanel is the very top of the page, the title bar
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
			// viewPostForm is the post at the top of the screen
		JPanel viewPostForm = createPostViewForm();
		mainPanel.add(viewPostForm, BorderLayout.CENTER);
		
			// viewResponsePanel is the list of all responses below the post
		JPanel viewResponsePanel = createResponsesPane();
		mainPanel.add(viewResponsePanel, BorderLayout.SOUTH);
		
			// Runs invokeLater in order to update the scroll bars properly.  Not sure what is
			// 		making it change after setting it to 0.
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
			   	scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			   	scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			   	scrollPanel.setSize((int) dim.getWidth()-15, (int) dim.getHeight()-60);
			   	scrollPanel.getVerticalScrollBar().setValue(0);
			   	scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
			}
		});
		currentFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
			// Allow user to see the Frame with all attached panels.
		currentFrame.setVisible(true);
	}
}
